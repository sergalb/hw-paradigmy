package expression;

import expression.exceptions.DivideByZeroException;
import expression.exceptions.EvaluatingException;
import expression.exceptions.OverflowException;

public class CheckedDivide extends BinaryOperation {
    public CheckedDivide(TripleExpression firstArg, TripleExpression secondArg) {
        super(firstArg, secondArg);
    }

    @Override
    protected void check(int x, int y) throws EvaluatingException {
        if (y == 0) {
            throw new DivideByZeroException();
        }
        if (x == Integer.MIN_VALUE && y == -1) {
            throw new OverflowException();
        }
    }

    @Override
    protected int apply(int x, int y) throws EvaluatingException {
        check(x,y);
        return x/y;
    }
}
