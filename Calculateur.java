import java.util.Scanner;

public class Calculateur {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter first number: ");
        String firstNumber = scanner.nextLine();

        System.out.println("Enter second number: ");
        String secondNumber = scanner.nextLine();

        System.out.println("Enter operation: ");
        String operation = scanner.nextLine();

        try {
            Integer firstNumberInt = Integer.parseInt(firstNumber);
            Integer secondNumberInt = Integer.parseInt(secondNumber);
            calculation(firstNumberInt, secondNumberInt, operation);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number");
        }finally {
            scanner.close();
        }

    }
    public static void calculation(Integer firstNumber, Integer secondNumber, String operation){
        if (operation.equals("+")) {
            System.out.println(firstNumber + secondNumber);
        } else if (operation.equals("-")) {
            System.out.println(firstNumber - secondNumber);
        } else if (operation.equals("*")) {
            System.out.println(firstNumber * secondNumber);
        }  else {
            System.out.println("Operation not supported");
        }
    }
}