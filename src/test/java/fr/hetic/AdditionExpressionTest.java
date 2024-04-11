package fr.hetic;

import fr.hetic.expressions.AdditionExpression;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdditionExpressionTest {
    @Test
    public void testExecute() {
        AdditionExpression additionExpression = new AdditionExpression();
        assertEquals(5, additionExpression.evaluate(2, 3));
    }
}
