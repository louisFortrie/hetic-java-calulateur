package src;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import src.expressions.Expression;
import src.factories.OperationFactory;

class SyntaxErrorException extends Exception {
    public SyntaxErrorException(String message) {
        super(message);
    }
}

// Classe d'exception métier pour les opérations non supportées
class UnsupportedOperatorException extends Exception {
    public UnsupportedOperatorException(String message) {
        super(message);
    }
}

// Classe d'exception métier pour les erreurs lors de l'opération
class OperationExecutionException extends Exception {
    public OperationExecutionException(String message) {
        super(message);
    }
}

public class Calculateur {
    public static void main(String[] args) {
        if(args.length == 0){
            System.out.println("No arguments provided. Expected : <directoryName> or <fileName>");
            System.exit(1);
        }
        File file = new File(args[0]);
        processDirectory(file);
    }
    public static void processDirectory(File directory) {
        File[] files = directory.listFiles(file -> file.getName().endsWith(".op") || file.isDirectory());
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    processDirectory(file);
                } else {
                    String outputFileName = file.getPath().replace(".op", ".res");
                    processFile(file, new File(outputFileName));
                }
            }
        } else {
            System.out.println("File or Directory not found " + directory.getPath());
        }
    }

    public static void processFile(File inputFile, File outputFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] parts = line.split(" ");
                    checkSyntax(line);
                    if (parts.length == 3) {
                        Integer firstNumber = Integer.parseInt(parts[0]);
                        Integer secondNumber = Integer.parseInt(parts[1]);
                        String operation = parts[2];
                        Integer result = calculate(firstNumber, secondNumber, operation);
                        if (result != null) {
                            writer.write(firstNumber + " " + operation + " " + secondNumber + " = " + result);
                            writer.newLine();
                        } else {
                            throw new UnsupportedOperatorException(operation + " is not a valid operator");
                        }
                    } else {
                        throw new SyntaxErrorException(
                            "Invalid number of arguments expected 3 but found " + parts.length + " in line : " + line);
                    }
                } catch (Exception e) {
                    writer.write("Error: " + e.getMessage());
                    writer.newLine();
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading file");
        }
    }

    private static void checkSyntax(String line) throws SyntaxErrorException {
        String[] parts = line.split(" ");
        if (parts.length != 3) {
            throw new SyntaxErrorException(
                "Invalid number of arguments expected 3 but found " + parts.length + " in line : " + line);
        }
        if (!parts[0].matches("-?\\d+") || !parts[1].matches("-?\\d+")) {
            throw new SyntaxErrorException("Invalid syntax in line : " + line
                + " Expected <Integer> <Integer> <String> but found : " + parts[0] + " " + parts[1] + " " + parts[2]);
        }
    }

    private static Integer calculate(int a, int b, String operation) throws UnsupportedOperatorException{
        OperationFactory factory = new OperationFactory();
        Expression expression = factory.createExpression(operation);
        if(expression == null){
            throw new UnsupportedOperatorException(operation + " is not a valid operator");
        }
        return expression.evaluate(a, b);
    }
}