package expression;

import expression.exceptions.DivideByZeroException;
import expression.exceptions.IncorrectConstException;

public class UncheckedIntegerOperation implements Operation<Integer> {
    public Integer sum(Integer x, Integer y) {
        return x + y;
    }

    @Override
    public Integer sub(Integer x, Integer y) {
        return x - y;
    }

    @Override
    public Integer mul(Integer x, Integer y) {
        return x * y;
    }

    @Override
    public Integer div(Integer x, Integer y) throws DivideByZeroException {
        if (y == 0) {
            throw new DivideByZeroException();
        }
        return x / y;
    }
    @Override
    public Integer negate(Integer x) {
        return -x;
    }

    public Integer nextNumber(String number) throws IncorrectConstException {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IncorrectConstException();
        }
    }
}

