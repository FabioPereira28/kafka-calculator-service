import com.wit.calculator.service.CalculatorService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorServiceTest {

    private final CalculatorService service = new CalculatorService();

    @Test
    void testSum() {
        double r = service.sum(2.0, 3.0);
        Assertions.assertEquals(5.0, r);
    }

    @Test
    void testSubtract() {
        double r = service.subtract(10.0, 4.0);
        Assertions.assertEquals(6.0, r);
    }

    @Test
    void testMultiply() {
        double r = service.multiply(3.0, 5.0);
        Assertions.assertEquals(15.0, r);
    }

    @Test
    void testDivide() {
        double r = service.divide(10.0, 2.0);
        Assertions.assertEquals(5.0, r);
    }
}

