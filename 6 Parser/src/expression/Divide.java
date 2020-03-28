package expression;

public class Divide extends BinaryOperation {
    public Divide(CommonExpression firstArg, CommonExpression secondArg) {
        super(firstArg, secondArg);
    }

    @Override
    protected int apply(int x, int y) {
        //assert (y != 0) : "Divide by zero";
        return x/y;
    }

    @Override
    protected double apply(double x, double y) {
        //assert (y != 0) : "Divide by zero";
        return x/y;
    }
}
