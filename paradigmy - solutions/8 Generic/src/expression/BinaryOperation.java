package expression;

import expression.exceptions.EvaluatingException;
import expression.exceptions.IncorrectConstException;

import java.math.BigInteger;

abstract class BinaryOperation<T> implements TripleExpression<T> {
    private TripleExpression<T> firstArg;
    private TripleExpression<T> secondArg;
    protected Operation<T> operation;

    BinaryOperation(TripleExpression<T> firstArg, TripleExpression<T> secondArg, Operation<T> operation) {
        this.firstArg = firstArg;
        this.secondArg = secondArg;
        this.operation = operation;
    }

    protected abstract T apply(T x, T y) throws EvaluatingException;

    public T evaluate(T x, T y, T z) throws Exception {
        return apply(firstArg.evaluate(x, y, z), secondArg.evaluate(x, y, z));
    }

}
