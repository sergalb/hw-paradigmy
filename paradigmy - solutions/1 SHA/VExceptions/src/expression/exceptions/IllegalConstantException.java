package expression.exceptions;

public class IllegalConstantException extends ParsingException {
    public IllegalConstantException(final String Const, final int ind) {
        super("Constant '" + Const + "' is unsuitable for int at position: " + ind );
    }
}
