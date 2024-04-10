package src.expressions;

public class MultiplicationExpression extends Expression {

    @Override
    public
    int evaluate(int a, int b) {
        return a * b;
    }
}