package tests;
import main.Passageiro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class PassageiroTest {
    private Passageiro passageiro;

    @Test
    public void testPassageiroNulo(){
        assertEquals(null, this.passageiro);
    }

}
