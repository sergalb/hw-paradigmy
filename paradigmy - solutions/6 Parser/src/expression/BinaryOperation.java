package expression;

abstract class BinaryOperation implements CommonExpression {
    private CommonExpression firstArg;
    private CommonExpression secondArg;

    BinaryOperation(CommonExpression firstArg, CommonExpression secondArg) {
        this.firstArg = firstArg;
        this.secondArg = secondArg;
    }

    protected abstract int apply(int x, int y);

    protected abstract double apply(double x, double y);


    public int evaluate(int value) {
        return apply(firstArg.evaluate(value), secondArg.evaluate(value));
    }

    public double evaluate(double value) {
        return apply(firstArg.evaluate(value), secondArg.evaluate(value));
    }

    public int evaluate(int x, int y, int z) {
        return apply(firstArg.evaluate(x, y, z), secondArg.evaluate(x, y, z));
    }
}
