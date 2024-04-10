package src.expressions;

public class AdditionExpression extends Expression {

    @Override
    public
    int evaluate(int a, int b) {
        return a + b;
    }
}