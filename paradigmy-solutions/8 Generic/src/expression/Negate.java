package expression;

import expression.exceptions.OverflowException;

public class Negate<T> extends UnaryOperation<T> {
    public Negate(TripleExpression<T> argument, Operation<T> operation) {
        super(argument, operation);
    }

    @Override
    protected T apply(T x) throws OverflowException {
        return operation.negate(x);
    }
}
