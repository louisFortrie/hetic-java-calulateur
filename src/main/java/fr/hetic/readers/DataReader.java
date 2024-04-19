package fr.hetic.readers;

import fr.hetic.Expression;
import fr.hetic.OperationFactory;

class LineProcessingException extends Exception {
    public LineProcessingException(String message) {
        super(message);
    }
}

class SyntaxErrorException extends Exception {
    public SyntaxErrorException(String message) {
        super(message);
    }
}

class UnsupportedOperatorException extends Exception {
    public UnsupportedOperatorException(String message) {
        super(message);
    }
}

public interface DataReader {
    void process();
    public static String processLine (String line) throws LineProcessingException {
        try {
            String[] parts = line.split(" ");
            checkSyntax(line);
            
                Integer firstNumber = Integer.parseInt(parts[0]);
                Integer secondNumber = Integer.parseInt(parts[1]);
                String operation = parts[2];
                Integer result = calculate(firstNumber, secondNumber, operation);
                if (result != null) {
                    return firstNumber + " " + operation + " " + secondNumber + " = " + result;
                    // writer.write(firstNumber + " " + operation + " " + secondNumber + " = " + result);
                    // writer.newLine();
                } else {
                    throw new UnsupportedOperatorException(operation + " is not a valid operator");
                }
        } catch (Exception e) {
           throw new LineProcessingException(e.getMessage());
        }
    };
     public static void checkSyntax(String line) throws SyntaxErrorException {
        String[] parts = line.split(" ");
        if (parts.length != 3) {
            throw new SyntaxErrorException(
                "Invalid number of arguments expected 3 but found " + parts.length + " in line : " + line);
        }
        if (!parts[0].matches("-?\\d+") || !parts[1].matches("-?\\d+")) {
            throw new SyntaxErrorException("Invalid syntax in line : " + line
                + " Expected <Integer> <Integer> <String> but found : " + parts[0] + " " + parts[1] + " " + parts[2]);
        }
    };
    public static Integer calculate(int a, int b, String operation) throws UnsupportedOperatorException{
        OperationFactory factory = new OperationFactory();
        Expression expression = factory.createExpression(operation);
        if(expression == null){
            throw new UnsupportedOperatorException(operation + " is not a valid operator");
        }
        return expression.evaluate(a, b);
    };
}
