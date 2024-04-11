package fr.hetic;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import fr.hetic.expressions.MultiplicationExpression;

public class MultiplicationExpressionTest {
    @Test
    public void testExecute() {
        MultiplicationExpression multiplicationExpression = new MultiplicationExpression();
        assertEquals(6, multiplicationExpression.evaluate(2, 3));
    }
}
