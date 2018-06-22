package expression;

import expression.exceptions.EvaluatingException;

public abstract class UnaryOperation<T> implements TripleExpression<T> {
    private final TripleExpression<T> argument;
    protected Operation<T> operation;

    UnaryOperation(TripleExpression<T> argument, Operation<T> operation) {
        this.argument = argument;
        this.operation = operation;
    }

    protected abstract T apply(final T x) throws EvaluatingException;

    @Override
    public T evaluate(T x, T y, T z) throws Exception {
        return apply(argument.evaluate(x, y, z));
    }
}
