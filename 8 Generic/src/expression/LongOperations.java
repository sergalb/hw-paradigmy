package expression;

import expression.exceptions.DivideByZeroException;
import expression.exceptions.EvaluatingException;
import expression.exceptions.IncorrectConstException;
import expression.exceptions.OverflowException;

public class LongOperations implements Operation<Long> {

    @Override
    public Long sum(Long x, Long y) throws OverflowException {
        return x+y;
    }

    @Override
    public Long sub(Long x, Long y) throws OverflowException {
        return x - y;
    }

    @Override
    public Long mul(Long x, Long y) {
        return x * y;
    }

    @Override
    public Long div(Long x, Long y) throws DivideByZeroException {
        if (y == 0) {
            throw new DivideByZeroException();
        }
        return x / y;
    }

    @Override
    public Long negate(Long x) throws OverflowException {
        return -x;
    }

    public Long nextNumber(String number) throws IncorrectConstException {
        try {
            return Long.parseLong(number);
        } catch (NumberFormatException e) {
            throw new IncorrectConstException();
        }
    }
}
