package expression.exceptions;

public class IllegalTextException extends ParsingException {
    public IllegalTextException(int ind) {
        super("Illegal text in expression start from " + ind);
    }
}
