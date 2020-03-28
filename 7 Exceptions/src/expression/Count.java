package expression;

import expression.exceptions.EvaluatingException;

public class Count extends UnaryOperation {
    public Count(TripleExpression argument) {
        super(argument);
    }

    @Override
    protected void check(int y) throws EvaluatingException {

    }

    @Override
    protected int apply(int x) {
        return Integer.bitCount(x);
    }

}
