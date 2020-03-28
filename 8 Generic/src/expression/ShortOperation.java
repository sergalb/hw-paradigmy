package expression;

import expression.exceptions.DivideByZeroException;
import expression.exceptions.EvaluatingException;
import expression.exceptions.IncorrectConstException;
import expression.exceptions.OverflowException;

public class ShortOperation implements Operation<Short> {
    @Override
    public Short sum(Short x, Short y) throws OverflowException {
        return (short) (x + y);
    }

    @Override
    public Short sub(Short x, Short y) throws OverflowException {
        return (short) (x - y);
    }

    @Override
    public Short mul(Short x, Short y) throws OverflowException {
        return (short) (x*y);
    }

    @Override
    public Short div(Short x, Short y) throws EvaluatingException {
        if (y == 0) {
            throw new DivideByZeroException();
        }
        return (short) (x / y);
    }

    @Override
    public Short negate(Short x) throws OverflowException {
        return (short) -x;
    }
    public Short nextNumber(String number) throws IncorrectConstException {
        try {
            return (short) Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IncorrectConstException();
        }
    }
}
