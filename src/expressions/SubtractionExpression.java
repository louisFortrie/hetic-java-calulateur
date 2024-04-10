package src.expressions;

public class SubtractionExpression extends Expression {

    @Override
    public
    int evaluate(int a, int b) {
        return a - b;
    }
}
