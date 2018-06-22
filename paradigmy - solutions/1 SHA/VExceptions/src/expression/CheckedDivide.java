package expression;

import expression.exceptions.IllegalOperationException;
import expression.exceptions.OverflowException;

public class CheckedDivide extends BinaryOperator {
    public CheckedDivide(final TripleExpression x, final TripleExpression y) {
        super(x, y);
    }

    protected void check(final int x, final int y) throws IllegalOperationException, OverflowException {
        if (y == 0) {
            throw new IllegalOperationException("Division by zero: ");
        }
        if (x == Integer.MIN_VALUE && y == -1) {
            throw new OverflowException();
        }
    }

    protected int result(final int x, final int y) throws IllegalOperationException, OverflowException {
        check(x, y);
        return x / y;
    }
}
