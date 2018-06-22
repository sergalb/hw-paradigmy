package expression.exceptions;

public class SuperfluousOpeningBracketsException extends ParsingException {
    public SuperfluousOpeningBracketsException(final String s, final int ind){
        super("Superfluous opening brackets at position: " + ind + ": " + s);
    }
}
