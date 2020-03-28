package expression;

import expression.exceptions.EvaluatingException;

public class Xor extends BinaryOperation {
    public Xor(TripleExpression firstArg, TripleExpression secondArg) {
        super(firstArg, secondArg);
    }

    @Override
    protected int apply(int x, int y) {
        return x^y;
    }

    @Override
    protected void check(int x, int y) throws EvaluatingException {
        
    }
}
