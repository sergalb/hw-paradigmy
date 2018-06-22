package expression.exceptions;

public class MissingOperationException extends ParsingException {
    public MissingOperationException(final int ind, final String expression) {
        super("Missing operation before position: " + ind);
        super.pointer(ind, expression);
    }
}
