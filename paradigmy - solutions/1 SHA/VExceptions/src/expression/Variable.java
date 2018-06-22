package expression;

public class Variable implements TripleExpression {
    private final char varName;

    public Variable(final char x) {
        varName = x;
    }

    public Variable(final String x) {
        varName = x.charAt(0);
    }

    public int evaluate(final int x, final int y, final int z) {
        switch (varName) {
            case 'x':
                return x;
            case 'y':
                return y;
            case 'z':
                return z;
            default:
                return 0;
        }
    }

}
