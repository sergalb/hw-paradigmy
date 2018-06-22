package expression;

import expression.exceptions.EvaluatingException;

public class Or extends BinaryOperation {
    public Or(TripleExpression firstArg, TripleExpression secondArg) {
        super(firstArg, secondArg);
    }

    @Override
    protected int apply(int x, int y) {
        return x|y;
    }

    @Override
    protected void check(int x, int y) throws EvaluatingException {

    }
}
