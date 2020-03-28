package expression.exceptions;

public class DivideException extends EvaluatingException {
    public DivideException(int x, int y) {
        super("Result of divide " + x + "/" + y + " isn't int");
    }
}
