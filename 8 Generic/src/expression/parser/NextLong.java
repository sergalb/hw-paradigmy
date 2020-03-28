package expression.parser;

import expression.exceptions.IncorrectConstException;

public class NextLong implements NextNumber<Long> {
    @Override
    public Long nextNumber(String number) throws IncorrectConstException {
        try {
            return Long.parseLong(number);
        } catch (NumberFormatException e) {
            throw new IncorrectConstException();
        }
    }
}
