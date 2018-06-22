package expression;

import expression.exceptions.DivideByZeroException;
import expression.exceptions.EvaluatingException;
import expression.exceptions.OverflowException;
import expression.exceptions.ParsingException;

public interface Operation<T> {
    T sum(T x, T y) throws OverflowException;
    T sub(T x, T y) throws OverflowException;
    T mul(T x, T y) throws OverflowException;
    T div(T x, T y) throws EvaluatingException;
    T negate(T x) throws OverflowException;
    T nextNumber(String expression) throws ParsingException;
}
