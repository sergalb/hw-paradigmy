package expression;

import expression.exceptions.OverflowException;

public class Multiply<T> extends BinaryOperation<T> {
    public Multiply(TripleExpression<T> firstArg, TripleExpression<T> secondArg, Operation<T> operation) {
        super(firstArg, secondArg, operation);
    }

    @Override
    protected T apply(T x, T y) throws OverflowException {
        return operation.mul(x, y);
    }
}
