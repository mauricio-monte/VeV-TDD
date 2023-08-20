package tests;

import main.Voo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class VooTest {

    private Voo voo;

    @Test
    public void testVooNulo(){
        assertEquals(null, this.voo);
    }

    @Test
    public void testAdcVoo(){
        this.voo = new Voo("Origem A", "Destino A", "20/08/2023", "10:00", 200.0);

        assertEquals("Origem A", this.voo.getOrigem());
        assertEquals("Destino A", this.voo.getDestino());
        assertEquals("20/08/2023", this.voo.getData());
        assertEquals("10:00", this.voo.getHorario());
        assertEquals(200.0, this.voo.getPreco());
    }
}
