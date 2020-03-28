package expression.exceptions;

public class EndOfExpression extends ParsingException {
    public EndOfExpression() {
        super("The expression suddenly ended");
    }
}
