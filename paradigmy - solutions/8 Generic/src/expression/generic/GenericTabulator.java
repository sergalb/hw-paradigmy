package expression.generic;

import expression.exceptions.*;
import expression.*;
import expression.parser.*;

public class GenericTabulator implements Tabulator {

    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
        return makeTable(mode.charAt(0), expression, x1, x2, y1, y2, z1, z2);
    }

    private <T> Object[][][] makeTable(char mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
        Object[][][] res = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        Parser<T> parser = null;
        switch (mode) {
            case 'i':
                parser = (Parser<T>) new ExpressionParser<>(new IntegerOperation());
                break;
            case 'd':
                parser = (Parser<T>) new ExpressionParser<>(new DoubleOperation());
                break;
            case 'b':
                parser = (Parser<T>) new ExpressionParser<>(new BigIntegerOperation());
                break;
            case 'l':
                parser = (Parser<T>) new ExpressionParser<>(new LongOperations());
                break;
            case 'u':
                parser = (Parser<T>) new ExpressionParser<>(new UncheckedIntegerOperation());
                break;
            case 's':
                parser = (Parser<T>) new ExpressionParser<>(new ShortOperation());
                break;
        }
        //Parser<T> parser = new ExpressionParser<>(mode);
        NextNumber<T> nextNumber = null;
        switch (mode) {
            case 'i':
                nextNumber = (NextNumber<T>) new NextInteger();
                break;
            case 'd':
                nextNumber = (NextNumber<T>) new NextDouble();
                break;
            case 'b':
                nextNumber = (NextNumber<T>) new NextBigInteger();
                break;
            case 'l':
                nextNumber = (NextNumber<T>) new NextLong();
                break;
            case 'u':
                nextNumber = (NextNumber<T>) new NextInteger();
                break;
            case 's':
                nextNumber = (NextNumber<T>) new NextShort();
                break;
        }

        TripleExpression<T> exp;
        try {
            exp = parser.parse(expression);
        } catch (Exception e) {
            return res;
        }

        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                for (int k = z1; k <= z2; k++) {
                    try {
                        res[i - x1][j - y1][k - z1] = exp.evaluate(nextNumber.nextNumber(Integer.toString(i)), nextNumber.nextNumber(Integer.toString(j)), nextNumber.nextNumber(Integer.toString(k)));
                    } catch (ParsingException | EvaluatingException e) {
                        res[i - x1][j - y1][k - z1] = null;
                    }
                }
            }
        }
        return res;
    }
}