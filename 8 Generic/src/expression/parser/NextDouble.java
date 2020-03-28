package expression.parser;

import expression.exceptions.IncorrectConstException;

public class NextDouble implements NextNumber<Double> {
    @Override
    public Double nextNumber(String number) throws IncorrectConstException {
        try {
            return Double.parseDouble(number);
        } catch (NumberFormatException NFM) {
            throw new IncorrectConstException();
        }
    }
}
