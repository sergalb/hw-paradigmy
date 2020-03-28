package expression.exceptions;

public class DivideByZeroException extends EvaluatingException {
    public DivideByZeroException() {
        super("division by zero");
    }
}
