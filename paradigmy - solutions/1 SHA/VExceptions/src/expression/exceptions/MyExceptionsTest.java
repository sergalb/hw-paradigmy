package expression.exceptions;

import expression.parser.ExpressionParser;

import java.util.Scanner;

public class MyExceptionsTest
{
    public static void main(String[] args) throws ParsingException, EvaluatingException {
        Scanner reader = new Scanner(System.in);
        ExpressionParser parser = new ExpressionParser();
        System.out.println(parser.parse(reader.nextLine()).evaluate(reader.nextInt(), reader.nextInt(), reader.nextInt()));
    }
}
