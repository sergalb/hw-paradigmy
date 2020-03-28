package expression.parser;

import java.util.Scanner;

public class MyParserTest {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        ExpressionParser parser = new ExpressionParser();
        System.out.println(parser.parse(reader.nextLine()).evaluate(-2147483648, 0, 0));
    }
}
