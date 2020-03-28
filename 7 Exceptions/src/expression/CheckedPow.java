package expression;

import expression.exceptions.EvaluatingException;
import expression.exceptions.IllegalOperationException;
import expression.exceptions.OverflowException;

public class CheckedPow extends UnaryOperation {

    public CheckedPow(TripleExpression argument) {
        super(argument);
    }

    protected void check(int x) throws EvaluatingException {
        if (x >= 10) {
            throw new OverflowException();
        }
        else if (x < 0)
            throw new IllegalOperationException("Pow have negative argument");
    }

    protected int apply(int x) throws EvaluatingException {
        check(x);
        int res = 1;
        for (int i = 0; i < x; i++)
            res *= 10;
        return res;
    }
}


