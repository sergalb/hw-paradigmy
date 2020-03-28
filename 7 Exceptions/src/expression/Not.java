package expression;

import expression.exceptions.EvaluatingException;

public class Not extends UnaryOperation {
    public Not(TripleExpression argument) {
        super(argument);
    }

    @Override
    protected void check(int y) throws EvaluatingException {

    }

    @Override
    protected int apply(int x) {
        return ~x;
    }

}
