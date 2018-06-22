package expression;

import expression.exceptions.OverflowException;

public class Add<T> extends BinaryOperation<T> {
    public Add(TripleExpression<T> firstArg, TripleExpression<T> secondArg, Operation<T> operation) {
        super(firstArg, secondArg, operation);
    }

    @Override
    protected T apply(T x, T y) throws OverflowException {
        return operation.sum(x, y);
    }

}
