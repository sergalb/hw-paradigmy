package expression.exceptions;

public class SuperfluousClosingBracketsException extends ParsingException {
    public SuperfluousClosingBracketsException(final String s, final int ind){
        super("Superfluous closing brackets at position: " + ind + ": " + s);
    }
}
