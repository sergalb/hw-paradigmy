package expression;

public class Count extends UnaryOperation {
    public Count(CommonExpression argument) {
        super(argument);
    }

    @Override
    protected int apply(int x) {
        return Integer.bitCount(x);
    }

    @Override
    public double evaluate(double x) {
        return 0;
    }

    @Override
    public int evaluate(int value) {
        return 0;
    }
}
