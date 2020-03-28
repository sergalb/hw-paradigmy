package expression.exceptions;

public class MissingOperandException extends ParsingException {
    public MissingOperandException(final int ind, final String expression) {
        super("Missing operand before position: " + ind);
        super.pointer(ind, expression);
    }
}
