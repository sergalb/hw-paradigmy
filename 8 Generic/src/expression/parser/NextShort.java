package expression.parser;

import expression.exceptions.IncorrectConstException;

public class NextShort implements NextNumber<Short> {
    @Override
    public Short nextNumber(String number) throws IncorrectConstException {
        try {
            return (short) Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IncorrectConstException();
        }
    }
}
