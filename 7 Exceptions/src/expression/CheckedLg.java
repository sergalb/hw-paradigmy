package expression;

import expression.exceptions.EvaluatingException;
import expression.exceptions.IllegalOperationException;
import expression.exceptions.OverflowException;

public class CheckedLg extends UnaryOperation{
    public CheckedLg(TripleExpression argument) {
        super(argument);
    }

    private int binarySearch(int x) throws EvaluatingException {
        int left = -1;
        int right = 9;
        int mid;
        while (right - left > 1) {
            mid = (left + right) / 2;
            int res = new CheckedPow(new Const(10)).evaluate(0,0,0);
            if (res <= x) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }
    protected void check(int arg) throws IllegalOperationException {
        if (arg <= 0) {
            throw new IllegalOperationException("Log's argument less or equal zero: " + arg);
        }
    }

    protected int apply(int arg) throws EvaluatingException {
        check(arg);
        int res = 0;
        try {
            while (new CheckedPow(new Const(res + 1)).evaluate(0, 0, 0) <= arg) {
                res++;
            }
        } catch (OverflowException ignored) {
        }
        return res;
    }
}
