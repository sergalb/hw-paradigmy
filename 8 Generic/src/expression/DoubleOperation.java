package expression;

import expression.exceptions.EvaluatingException;
import expression.exceptions.IncorrectConstException;
import expression.exceptions.OverflowException;

public class DoubleOperation implements Operation<Double> {
    @Override
    public Double sum(Double x, Double y) throws OverflowException {
        /*if (x > 0 && Double.MAX_VALUE - x < y) {
            throw new OverflowException();
        }
        if (x < 0 && Double.MIN_VALUE - x > y) {
            throw new OverflowException();
        }*/
        return x + y;
    }

    @Override
    public Double sub(Double x, Double y) throws OverflowException {
        /*if (x >= 0 && y < 0 && x - Double.MAX_VALUE > y) {
            throw new OverflowException();
        }
        if (x <= 0 && y > 0 && Double.MIN_VALUE - x > -y) {
            throw new OverflowException();
        }*/
        return x - y;
    }

    @Override
    public Double mul(Double x, Double y) throws OverflowException {
        /*if (x > 0 && y > 0 && Double.MAX_VALUE / x < y) {
            throw new OverflowException();
        }
        if (x > 0 && y < 0 && Double.MIN_VALUE / x > y) {
            throw new OverflowException();
        }
        if (x < 0 && y > 0 && Double.MIN_VALUE / y > x) {
            throw new OverflowException();
        }
        if (x < 0 && y < 0 && Double.MAX_VALUE / x > y) {
            throw new OverflowException();
        }*/
        return x * y;
    }

    @Override
    public Double div(Double x, Double y) throws EvaluatingException {
        //if (y == 0) {
        // return Double.MIN_VALUE;
        //throw new DivideByZeroException();
        //}
        /*if (x == Double.MIN_VALUE && y == -1) {
            throw new OverflowException();
        }*/
        return x / y;
    }

    @Override
    public Double negate(Double x) throws OverflowException {
        /*if (x == Double.MIN_VALUE) {
            throw new OverflowException();
        }*/
        return -x;
    }

    public Double nextNumber(String number) throws IncorrectConstException {
        try {
            return Double.parseDouble(number);
        } catch (NumberFormatException NFM) {
            throw new IncorrectConstException();
        }
    }
}
