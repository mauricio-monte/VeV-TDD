package tests;

import main.Voo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class VooTeste {

    private Voo voo;

    public void construir(){
        this.voo = new Voo("Origem A", "Destino A", "20/08/2023", "10:00", 200.0);
    }

    public void destruir(){
        this.voo = null;
    }

    @Test
    public void testeVooNulo(){
        assertEquals(null, this.voo);
    }

    @Test
    public void testeAdcVoo(){
        construir();

        assertEquals("Origem A", this.voo.getOrigem());
        assertEquals("Destino A", this.voo.getDestino());
        assertEquals("20/08/2023", this.voo.getData());
        assertEquals("10:00", this.voo.getHorario());
        assertEquals(200.0, this.voo.getPreco());
        assertEquals(30, this.voo.VAGAS_INICIAIS);

        destruir();
    }

    @Test
    public void testeReservaVagasPadrao(){
        construir();

        this.voo.reservaVagas(5);

        assertEquals(25, this.voo.getVagasAtuais());

        destruir();
    }

    @Test
    public void testeReservaVagasAbaixoDoMinimo(){
        construir();

        try {
            this.voo.reservaVagas(35);
            assert(false);
        } catch (IndexOutOfBoundsException e){
            assertEquals("Vagas insuficientes.", e.getMessage());
        }

        destruir();
    }

}
