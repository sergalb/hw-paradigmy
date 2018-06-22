package expression.exceptions;

public class SuperfluousClosingBracketException extends ParsingException {
    public SuperfluousClosingBracketException(final int ind){
        super("Superfluous closing bracket at position: " + ind);
    }
}
