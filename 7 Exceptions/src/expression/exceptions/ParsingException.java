package expression.exceptions;

public class ParsingException extends Exception {
    public ParsingException(final String message) {
        super(message);
    }

    void pointer(final int ind, final String expression) {
        int start = 0;
        if (ind > 16) {
            start = ind - 16;
        }
        System.out.println(expression.substring(start, ind));
        for (int i = start; i < ind; i++) {
            System.out.print('=');
        }
        System.out.println('^');

    }
}
