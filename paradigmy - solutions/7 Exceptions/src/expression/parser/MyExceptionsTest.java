package expression.parser;

import expression.exceptions.EvaluatingException;
import expression.exceptions.ParsingException;

import java.util.Scanner;

public class MyExceptionsTest {
    public static void main(String[] args) throws ParsingException, EvaluatingException {
        Scanner reader = new Scanner(System.in);
        //if (0 > Integer.MIN_VALUE) { System.out.println("dasd"); }
        ExpressionParser parser = new ExpressionParser();
        System.out.println(parser.parse(Long.toString(1L << 31)).evaluate(0, 0, 9));
    }
}
