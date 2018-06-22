package expression.parser;


public class Start {
    public static void main(String[] args) {
        ExpressionParser parser = new ExpressionParser();
        try {
            System.out.print(parser.parse("5000000 * 5000000 * 100000").evaluate(2, 18427768, 3));
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}
// -2 + 5