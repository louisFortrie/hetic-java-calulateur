package fr.hetic.expressions;

import fr.hetic.Expression;

public class MultiplicationExpression implements Expression {

    @Override
    public
    int evaluate(int a, int b) {
        return a * b;
    }
}