package expression;

import expression.exceptions.OverflowException;

public class Subtract<T> extends BinaryOperation<T>{
    public Subtract(TripleExpression<T> firstArg, TripleExpression<T> secondArg, Operation<T> operation) {
        super(firstArg, secondArg, operation);
    }

    @Override
    protected T apply(T x, T y) throws OverflowException {
        return operation.sub(x, y);
    }

}
