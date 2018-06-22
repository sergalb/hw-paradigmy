package expression;

public class Main {
    public static void main(String[] args) {
        System.out.println(new Add(
                new Subtract(
                new Negate(new Variable("x"), new Variable("x")),
                new Negate(new Const(2), new Variable("x"))),
                    new Const(1)).evaluate(Integer.parseInt(args[0])));
    }
}
