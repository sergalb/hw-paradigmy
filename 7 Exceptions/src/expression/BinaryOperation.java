package expression;

import expression.exceptions.EvaluatingException;

abstract class BinaryOperation implements TripleExpression {
    private TripleExpression firstArg;
    private TripleExpression secondArg;

    BinaryOperation(TripleExpression firstArg, TripleExpression secondArg) {
        this.firstArg = firstArg;
        this.secondArg = secondArg;
    }

    protected abstract void check(int x, int y) throws EvaluatingException;

    protected abstract int apply(int x, int y) throws EvaluatingException;

    public int evaluate(int x, int y, int z) throws EvaluatingException {
        return apply(firstArg.evaluate(x, y, z), secondArg.evaluate(x, y, z));
    }
}
