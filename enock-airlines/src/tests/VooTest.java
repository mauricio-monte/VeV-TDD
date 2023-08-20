package tests;

import main.Voo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class VooTest {

    private Voo voo;

    @Test
    public void testVooNulo(){
        assertEquals(this.voo, null);
    }
}
