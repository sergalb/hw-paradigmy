package expression.parser;

import expression.exceptions.IncorrectConstException;

public class NextInteger implements NextNumber<Integer>{
    @Override
    public Integer nextNumber(String number) throws IncorrectConstException {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IncorrectConstException();
        }
    }
}
