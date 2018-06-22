package expression;

import expression.exceptions.EvaluatingException;

public abstract class UnaryOperator implements TripleExpression {
    private final TripleExpression operand;

    public UnaryOperator(final TripleExpression operand) {
        this.operand = operand;
    }

    protected abstract void check(final int x) throws EvaluatingException;

    protected abstract int result(final int x) throws EvaluatingException;

    public int evaluate(final int x, final int y, final int z) throws EvaluatingException {
        return result(operand.evaluate(x, y, z));
    }
}
