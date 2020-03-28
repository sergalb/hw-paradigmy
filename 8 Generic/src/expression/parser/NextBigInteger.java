package expression.parser;

import expression.exceptions.IncorrectConstException;

import java.math.BigInteger;

public class NextBigInteger implements NextNumber<BigInteger> {
    @Override
    public BigInteger nextNumber(String number) throws IncorrectConstException {
        try {
            return new BigInteger(number);
        } catch (NumberFormatException e) {
            throw new IncorrectConstException();
        }
    }
}
