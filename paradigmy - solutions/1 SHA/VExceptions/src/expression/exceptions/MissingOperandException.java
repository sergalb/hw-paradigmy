package expression.exceptions;

public class MissingOperandException extends ParsingException {
    public MissingOperandException(final String e, final String s, final int ind) {
        super("Missing " + e + " operand at position " + ind + ": " + s);
    }
}
