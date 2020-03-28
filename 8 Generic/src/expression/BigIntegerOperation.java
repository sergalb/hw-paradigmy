package expression;

import expression.exceptions.DivideByZeroException;
import expression.exceptions.IncorrectConstException;

import java.math.BigInteger;

public class BigIntegerOperation implements Operation<BigInteger> {

    @Override
    public BigInteger sum(BigInteger x, BigInteger y) {
        return x.add(y);
    }

    @Override
    public BigInteger sub(BigInteger x, BigInteger y) {
        return x.subtract(y);
    }

    @Override
    public BigInteger mul(BigInteger x, BigInteger y) {
        return x.multiply(y);
    }

    @Override
    public BigInteger div(BigInteger x, BigInteger y) throws DivideByZeroException {
        if (y.equals(BigInteger.ZERO)) {
            throw new DivideByZeroException();
        }
        return x.divide(y);
    }

    @Override
    public BigInteger negate(BigInteger x) {
        return x.negate();
    }

    public BigInteger nextNumber(String number) throws IncorrectConstException {
        try {
            return new BigInteger(number);
        } catch (NumberFormatException e) {
            throw new IncorrectConstException();
        }
    }
}
