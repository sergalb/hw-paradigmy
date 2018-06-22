package expression;

import expression.exceptions.EvaluatingException;

public class And extends BinaryOperation {
    public And(TripleExpression firstArg, TripleExpression secondArg) {
        super(firstArg, secondArg);
    }

    @Override
    protected void check(int x, int y) throws EvaluatingException {

    }

    @Override
    protected int apply(int x, int y) {
        return x & y;
    }
}
