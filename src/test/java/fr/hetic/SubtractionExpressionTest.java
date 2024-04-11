package fr.hetic;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import fr.hetic.expressions.SubtractionExpression;

public class SubtractionExpressionTest {
    @Test
    public void testExecute() {
        SubtractionExpression subtractionExpression = new SubtractionExpression();
        assertEquals(1, subtractionExpression.evaluate(3, 2));
    }
}
