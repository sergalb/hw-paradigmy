package expression.parser;

import expression.exceptions.IncorrectConstException;

public interface NextNumber<T> {
    T nextNumber(String number) throws IncorrectConstException;
}
