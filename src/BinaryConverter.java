import java.util.Scanner;

public class BinaryConverter {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("What number would you like to convert to binary?");
        int x = in.nextInt();
        System.out.println(x + " in binary would be " + Integer.toBinaryString(x));
    }
}
