package expression;

public class Not extends UnaryOperation {
    public Not(CommonExpression argument) {
        super(argument);
    }

    @Override
    protected int apply(int x) {
        return ~x;
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
