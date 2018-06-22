package expression;

import expression.exceptions.EvaluatingException;

public abstract class UnaryOperation implements TripleExpression {
    private final TripleExpression argument;

    UnaryOperation(TripleExpression argument) {
        this.argument = argument;
    }
    protected abstract void check(final int x) throws EvaluatingException;

    protected abstract int apply(final int x) throws EvaluatingException;

    @Override
    public int evaluate(int x, int y, int z) throws EvaluatingException {
        return apply(argument.evaluate(x, y, z));
    }
}
