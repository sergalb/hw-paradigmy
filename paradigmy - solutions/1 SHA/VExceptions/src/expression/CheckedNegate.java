package expression;

import expression.exceptions.OverflowException;

public class CheckedNegate extends UnaryOperator {
    public CheckedNegate(final TripleExpression x) {
        super(x);
    }

    protected void check(final int x) throws OverflowException {
        if (x == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
    }

    protected int result(final int x) throws OverflowException {
        check(x);
        return -x;
    }
}
