package expression.exceptions;

public class IllegalConstantException extends ParsingException {
    public IllegalConstantException(final String reason, final int ind, final String expression) {
        super("Constant '" + reason + "' is unsuitable for int at position: " + ind);
        super.pointer(ind, expression);
    }
}
