package org.stjs.generator.writer.declaration;

import com.sun.source.tree.BlockTree;
import com.sun.source.tree.ClassTree;
import com.sun.source.tree.Tree;
import org.stjs.generator.GenerationContext;
import org.stjs.generator.javac.TreeUtils;
import org.stjs.generator.javac.TreeWrapper;
import org.stjs.generator.javascript.AssignOperator;
import org.stjs.generator.javascript.JavaScriptBuilder;
import org.stjs.generator.name.DependencyType;
import org.stjs.generator.utils.JavaNodes;
import org.stjs.generator.writer.JavascriptKeywords;
import org.stjs.generator.writer.WriterVisitor;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import java.util.ArrayList;
import java.util.List;

public class InterfaceWriter<JS> {

	/**
	 * transform a.b.type in constructor.type
	 */
	private String replaceFullNameWithConstructor(String typeName) {
		int pos = typeName.lastIndexOf('.');
		return JavascriptKeywords.CONSTRUCTOR + typeName.substring(pos);
	}

	private List<Tree> getAllInterfaces(ClassTree clazz) {
		List<Tree> enums = new ArrayList<>();
		for (Tree member : clazz.getMembers()) {
			if (member instanceof ClassTree && member.getKind() == Tree.Kind.INTERFACE) {
				enums.add(member);
			}

			// Get enums recursively
			if (member instanceof ClassTree && member.getKind() == Tree.Kind.CLASS) {
				enums.addAll(getAllInterfaces((ClassTree) member));
			}
		}
		return enums;
	}

	public void generate(WriterVisitor<JS> visitor, ClassTree tree, GenerationContext<JS> context, List<JS> stmts) {
		Element type = TreeUtils.elementFromDeclaration(tree);
		boolean outerClass = type.getEnclosingElement().getKind() == ElementKind.PACKAGE;

		// Move member interfaces to the top level
		if (outerClass) {
			List<Tree> enums = getAllInterfaces(tree);
			for (Tree member : enums) {
				generateInterface(visitor, (ClassTree) member, context, stmts);
			}
		}

		// Render top level interfaces correctly
		if (type.getKind() == ElementKind.INTERFACE && outerClass) {
			generateInterface(visitor, tree, context, stmts);
		}
	}

	private List<Tree> getAllMembersExceptConstructors(ClassTree clazz) {
		List<Tree> nonConstructors = new ArrayList<>();
		for (Tree member : clazz.getMembers()) {
			if (!JavaNodes.isConstructor(member) && !(member instanceof BlockTree)) {
				nonConstructors.add(member);
			}
		}
		return nonConstructors;
	}

	private List<JS> getMembers(WriterVisitor<JS> visitor, ClassTree clazz, GenerationContext<JS> context) {
		// the following members must not appear in the initializer function:
		// - constructors (they are printed elsewhere)
		// - abstract methods (they should be omitted)

		List<Tree> nonConstructors = getAllMembersExceptConstructors(clazz);

		if (nonConstructors.isEmpty()) {
			return null;
		}

		List<JS> stmts = new ArrayList<>();
		for (Tree member : nonConstructors) {
			stmts.add(visitor.scan(member, context));
		}

		return stmts;
	}

	private DependencyType getDependencyTypeForClassDef(Element type) {
		if (JavaNodes.isInnerType(type)) {
			// this is an inner (anonymous or not) class -> STATIC dep type instead
			return DependencyType.STATIC;
		}
		return DependencyType.EXTENDS;
	}

	/**
	 * @return the list of implemented interfaces. for interfaces, the super class goes also in the interfaces list
	 */
	private List<JS> getInterfaces(ClassTree clazz, GenerationContext<JS> context) {
		Element type = TreeUtils.elementFromDeclaration(clazz);
		DependencyType depType = getDependencyTypeForClassDef(type);

		List<JS> ifaces = new ArrayList<>();
		for (Tree iface : clazz.getImplementsClause()) {
			TreeWrapper<Tree, JS> ifaceType = context.getCurrentWrapper().child(iface);
			if (!ifaceType.isSyntheticType()) {
				ifaces.add(context.js().name(ifaceType.getTypeName(depType)));
			}
		}

		if (clazz.getExtendsClause() != null && type.getKind() == ElementKind.INTERFACE) {
			TreeWrapper<Tree, JS> superType = context.getCurrentWrapper().child(clazz.getExtendsClause());
			if (!superType.isSyntheticType()) {
				ifaces.add(0, context.js().name(superType.getTypeName(DependencyType.EXTENDS)));
			}
		}
		return ifaces;
	}

	public void generateInterface(WriterVisitor<JS> visitor, ClassTree tree, GenerationContext<JS> context, List<JS> stmts) {
		Element type = TreeUtils.elementFromDeclaration(tree);
		if (type.getKind() != ElementKind.INTERFACE) {
			return;
		}

		// TODO :: implements
		List<JS> members = getMembers(visitor, tree, context);

		String typeName = context.getNames().getTypeName(context, type, DependencyType.EXTENDS);
		String originalTypeName = typeName;

		JavaScriptBuilder<JS> js = context.js();

		typeName = typeName.replace('.', '_');
		stmts.add(js.interfaceDeclaration(typeName, members, getInterfaces(tree, context)));

		boolean outerClass = type.getEnclosingElement().getKind() == ElementKind.PACKAGE;
		if (outerClass && !typeName.equals(originalTypeName)) {
			stmts.add(js.assignment(
					AssignOperator.ASSIGN,
					js.name(originalTypeName),
					js.name(typeName)
			));
		}
	}

	public void generateReference(WriterVisitor<JS> visitor, ClassTree tree, GenerationContext<JS> context, List<JS> stmts) {
		Element type = TreeUtils.elementFromDeclaration(tree);
		boolean innerClass = type.getEnclosingElement().getKind() != ElementKind.PACKAGE;

		if (!innerClass) {
			return;
		}

		String typeName = context.getNames().getTypeName(context, type, DependencyType.EXTENDS);

		// TODO :: change `leftSide` to `js.name(typeName.substring(pos))` once classes are implemented
		String leftSide = replaceFullNameWithConstructor(typeName);
		typeName = typeName.replace('.', '_');

		JavaScriptBuilder<JS> js = context.js();

		stmts.add(
				js.expressionStatement(
						js.assignment(
								AssignOperator.ASSIGN,
								js.name(leftSide),
								js.name(typeName)
						)
				)
		);
	}
}
