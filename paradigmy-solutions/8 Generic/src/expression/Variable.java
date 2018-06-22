package expression;

public class Variable<T> implements TripleExpression<T> {
    private String variable;

    public Variable(String variable) {
        this.variable = variable;
    }

    @Override
    public T evaluate(T x, T y, T z) throws Exception {
        switch (variable) {
            case "x" :
                return x;
            case "y" :
                return y;
            case "z" :
                return z;
            default:
                throw new Exception("incorrect variable name");
        }
    }

}
