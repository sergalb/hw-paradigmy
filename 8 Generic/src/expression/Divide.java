package expression;

import expression.exceptions.EvaluatingException;

public class Divide<T> extends BinaryOperation<T> {
    public Divide(TripleExpression<T> firstArg, TripleExpression<T> secondArg, Operation<T> operation) {
        super(firstArg, secondArg, operation);
    }

    @Override
    protected T apply(T x, T y) throws EvaluatingException {
        return operation.div(x, y);
    }
}
