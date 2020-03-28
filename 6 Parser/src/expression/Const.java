package expression;

public class Const implements CommonExpression {
    private final Number value;

    public Const(Number value) {
        this.value = value;
    }


    public int evaluate(int value) {
        return this.value.intValue();
    }

    public double evaluate(double value) {
        return this.value.doubleValue();
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return value.intValue();
    }
}
