package org.stjs.generator.javascript;

import java.io.File;
import java.io.Writer;

import com.google.debugging.sourcemap.SourceMapGenerator;

import javax.annotation.Nonnull;

/**
 * <p>StringJavaScriptBuilder class.</p>
 *
 * @author acraciun
 * @version $Id: $Id
 */
@SuppressWarnings("PMD.ExcessivePublicCount")
public class StringJavaScriptBuilder implements JavaScriptBuilder<String> {

	/** {@inheritDoc} */
	@Override
	public String asExpressionList(Iterable<String> nodes) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String block(Iterable<String> statements) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String elementGet(String target, String index) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String expressionStatement(String expr) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String array(Iterable<String> values) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String string(String value) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String paren(String expr) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String position(String node, int javaStartLineNumber, int javaStartColumnNumber, int javaEndLineNumber, int javaEndColumnNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String breakStatement(String label) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String caseStatement(String expression, Iterable<String> statements) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String catchClause(String contidion, String body) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String continueStatement(String label) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String function(String name, Iterable<String> params, String body) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String arrowFunction(Iterable<String> params, String body) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String name(CharSequence name) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String newExpression(String target, Iterable<String> arguments) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String object(Iterable<NameValue<String>> props) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String doLoop(String condition, String body) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String emptyStatement() {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String forInLoop(String iterator, String iterated, String body) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String forLoop(String init, String condition, String update, String body) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String ifStatement(String condition, String thenPart, String elsePart) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String addStatement(String blockOrStatement, String statement) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String addStatementBeginning(String blockOrStatement, String statement) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String labeledStatement(String label, String statement) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String returnStatement(String returnValue) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String switchStatement(String expr, Iterable<String> cases) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String functionCall(String target, Iterable<String> arguments) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String property(String target, CharSequence name) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String variableDeclaration(boolean statement, Iterable<NameValue<String>> vars, boolean isFinal) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String variableDeclaration(boolean statement, CharSequence name, String init, boolean isFinal) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String tryStatement(String tryBlock, Iterable<String> catchClauses, String finallyBlock) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String whileLoop(String condition, String body) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String root(Iterable<String> children) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String code(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String toString(String node) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String conditionalExpression(String test, String trueExpr, String falseExpr) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String character(String c) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String number(Number n) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String statements(Iterable<String> stmts) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String enumDeclaration(@Nonnull String name, @Nonnull Iterable<String> values) {
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String assignment(AssignOperator operator, String left, String right) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String binary(BinaryOperator operator, Iterable<String> operands) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String keyword(Keyword token) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String unary(UnaryOperator operator, String operand) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String label(CharSequence name) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public SourceMapGenerator writeJavaScript(String javascriptRoot, File inputFile, boolean generateSourceMap, Writer writer) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String comment(String node, String comment) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String throwStatement(String expr) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String emptyExpression() {
		// TODO Auto-generated method stub
		return null;
	}
}
