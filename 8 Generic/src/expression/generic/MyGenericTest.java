package expression.generic;

import java.util.Scanner;

public class MyGenericTest {
    public static void main(String[] args) throws Exception {
        Scanner reader = new Scanner(System.in);
        Tabulator tabulator = new GenericTabulator();
        Object[][][] res = tabulator.tabulate(reader.nextLine(), reader.nextLine(), reader.nextInt(),reader.nextInt(),reader.nextInt(),reader.nextInt(),reader.nextInt(),reader.nextInt());
        System.out.println(res[0][0][0]);
    }
}
