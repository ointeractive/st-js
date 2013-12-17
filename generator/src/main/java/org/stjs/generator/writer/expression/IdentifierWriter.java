package org.stjs.generator.writer.expression;

import java.util.Collections;
import java.util.List;

import javacutils.TreeUtils;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;

import org.mozilla.javascript.ast.AstNode;
import org.stjs.generator.GenerationContext;
import org.stjs.generator.GeneratorConstants;
import org.stjs.generator.utils.JavaNodes;
import org.stjs.generator.writer.MemberWriters;
import org.stjs.generator.writer.WriterContributor;
import org.stjs.generator.writer.WriterVisitor;

import com.sun.source.tree.IdentifierTree;

public class IdentifierWriter<JS> implements WriterContributor<IdentifierTree, JS> {

	// private void visitField(FieldWrapper field, NameExpr n) {
	// if (Modifier.isStatic(field.getModifiers())) {
	// printStaticFieldOrMethodAccessPrefix(field.getOwnerType(), true);
	// } else if (!isInlineObjectCreationChild(n, INLINE_CREATION_PARENT_LEVEL)) {
	// printer.print("this.");
	// }
	// }

	private List<AstNode> visitField(Element def, IdentifierTree tree, GenerationContext context) {
		return Collections.<AstNode>singletonList(JavaScriptNodes.property(MemberWriters.buildTarget(context, def), tree.getName().toString()));
	}

	private List<AstNode> visitEnumConstant(Element def, IdentifierTree tree, GenerationContext context) {
		AstNode target = JavaScriptNodes.name(context.getNames().getTypeName(context, def.getEnclosingElement()));
		return Collections.<AstNode>singletonList(JavaScriptNodes.property(target, tree.getName().toString()));
	}

	@Override
	public JS visit(WriterVisitor<JS> visitor, IdentifierTree tree, GenerationContext<JS> context) {

		String name = tree.getName().toString();

		if (GeneratorConstants.SPECIAL_THIS.equals(name) || GeneratorConstants.THIS.equals(name)) {
			return Collections.singletonList(JavaScriptNodes.THIS());
		}
		Element def = TreeUtils.elementFromUse(tree);
		assert def != null : "Cannot find the definition for variable " + tree.getName();

		if (def.getKind() == ElementKind.PACKAGE) {
			return Collections.emptyList();
		}
		if (def.getKind() == ElementKind.FIELD) {
			return visitField(def, tree, context);
		}
		if (def.getKind() == ElementKind.ENUM_CONSTANT) {
			return visitEnumConstant(def, tree, context);
		}
		if (def.getKind() == ElementKind.CLASS || def.getKind() == ElementKind.ENUM || def.getKind() == ElementKind.INTERFACE) {
			if (JavaNodes.isGlobal(def)) {
				return Collections.emptyList();
			}
			name = context.getNames().getTypeName(context, def);
		}

		// assume variable
		return Collections.<AstNode>singletonList(name(name));
	}

}
