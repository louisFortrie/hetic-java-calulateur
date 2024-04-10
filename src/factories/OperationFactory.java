package src.factories;

import src.expressions.*;

public class OperationFactory {

    public  Expression createExpression(String operationSymbol) {
        switch (operationSymbol) {
            case "+":
                return new AdditionExpression();
            case "-":
                return new SubtractionExpression();
            case "*":
                return new MultiplicationExpression();
            default:
                return null;
        }
    }
}