package expression;

import expression.exceptions.OverflowException;

public class CheckedNegate extends UnaryOperation {
    public CheckedNegate(TripleExpression argument) {
        super(argument);
    }

    @Override
    protected void check(int x) throws OverflowException{
        if (x == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
    }

    @Override
    protected int apply(int x) throws OverflowException {
        check(x);
        return -x;
    }
}
