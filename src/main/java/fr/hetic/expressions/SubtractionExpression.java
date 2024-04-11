package fr.hetic.expressions;

import fr.hetic.Expression;

public class SubtractionExpression implements Expression {

    @Override
    public int evaluate(int a, int b) {
        return a - b;
    }
}
