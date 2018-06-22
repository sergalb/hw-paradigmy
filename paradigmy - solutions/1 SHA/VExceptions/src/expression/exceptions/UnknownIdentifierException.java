package expression.exceptions;

public class UnknownIdentifierException extends ParsingException {
    public UnknownIdentifierException(final String s, final int pos) {
        super("Unknown symbol '" + s.charAt(pos) + "' " + "at position: " + pos + ": " + s);
    }
}
