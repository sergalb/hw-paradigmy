package expression;

public class Negate extends BinaryOperation {
    public Negate(CommonExpression firstArg, CommonExpression secondArg) {
        super(firstArg, secondArg);
    }

    @Override
    protected int apply(int x, int y) {
        return x*y;
    }

    @Override
    protected double apply(double x, double y) {
        return x*y;
    }
}
