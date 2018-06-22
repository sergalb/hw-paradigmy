package expression;

import expression.exceptions.EvaluatingException;

public abstract class BinaryOperator implements TripleExpression {
    private final TripleExpression left;
    private final TripleExpression right;

    public BinaryOperator(final TripleExpression left, final TripleExpression right) {
        this.left = left;
        this.right = right;
    }

    protected abstract void check(final int x, final int y) throws EvaluatingException;

    protected abstract int result(final int x, final int y) throws EvaluatingException;

    public int evaluate(final int x, final int y, final int z) throws EvaluatingException{
        return result(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }
}
