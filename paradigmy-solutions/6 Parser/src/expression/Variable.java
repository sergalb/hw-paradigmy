package expression;

public class Variable implements CommonExpression {
    private String variable;

    public Variable(String variable) {
        this.variable = variable;
    }

    public int evaluate(int value) {
        return value;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        switch (variable) {
            case "x" : return x;
            case "y" : return y;
            case "z" : return z;
            default : return -1;
        }
    }

    public double evaluate(double value) {
        return value;
    }

}
