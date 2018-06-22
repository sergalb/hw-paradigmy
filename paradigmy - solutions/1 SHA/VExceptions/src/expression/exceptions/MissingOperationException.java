package expression.exceptions;

public class MissingOperationException extends ParsingException {
    public MissingOperationException(final String s, final int ind) {
        super("Missing operation before position: " + ind + ": " + s);
    }
}
