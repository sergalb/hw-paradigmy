package expression.exceptions;

public class IllegalTextException extends ParsingException {
    public IllegalTextException(final int ind, final String expression) {
        super("Illegal text in expression start from " + ind);
        super.pointer(ind, expression);
    }
}
