package expression;

public abstract class UnaryOperation implements CommonExpression {
    private final CommonExpression argument;

    UnaryOperation(CommonExpression argument) {
        this.argument = argument;
    }
    protected abstract int apply(final int x);

    @Override
    public int evaluate(int x, int y, int z) {
        return apply(argument.evaluate(x, y, z));
    }
}
