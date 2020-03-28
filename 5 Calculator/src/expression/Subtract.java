package expression;

public class Subtract extends BinaryOperation {
    Subtract(CommonExpression firstArg, CommonExpression secondArg) {
        super(firstArg, secondArg);
    }

    @Override
    protected int apply(int x, int y) {
        return x - y;
    }

    @Override
    protected double apply(double x, double y) {
        return x - y;
    }
}
