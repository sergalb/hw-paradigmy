package expression.exceptions;

public class SuperfluousOpenBracketsException extends ParsingException {
    public SuperfluousOpenBracketsException() {
        super("Count of open brackets more then count of close brackets");
    }
}
