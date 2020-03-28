package expression;

public class Variable implements TripleExpression {
    private String variable;

    public Variable(String variable) {
        this.variable = variable;
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

}
