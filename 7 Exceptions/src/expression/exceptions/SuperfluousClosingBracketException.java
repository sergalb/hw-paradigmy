package expression.exceptions;

public class SuperfluousClosingBracketException extends ParsingException {
    public SuperfluousClosingBracketException(final int ind, final String expression){
        super("Superfluous closing bracket at position: " + ind);
        super.pointer(ind, expression);

    }
}
