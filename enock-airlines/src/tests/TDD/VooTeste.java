package tests.TDD;

import main.Passageiro;
import main.Voo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class VooTeste {

    private Voo voo;

    public void construir(){
        this.voo = new Voo("Origem A","Destino A", "20/08/2023",
                "10:00", 200.0);
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

        this.voo.reservaVagas(null, 5);

        assertEquals(25, this.voo.getVagasAtuais());

        destruir();
    }

    @Test
    public void testeReservaVagasAbaixoDoMinimo(){
        construir();

        try {
            this.voo.reservaVagas(null, 35);
            assert(false);
        } catch (IndexOutOfBoundsException e){
            assertEquals("Vagas insuficientes.", e.getMessage());
        }

        destruir();
    }

    @Test
    public void testeLiberaVagasPadrao(){
        construir();

        this.voo.reservaVagas(null, 5);

        this.voo.liberaVagas(5);

        assertEquals(30, this.voo.getVagasAtuais());

        destruir();
    }

    @Test
    public void testeLiberaVagasAcimaDoMaximo(){
        construir();

        this.voo.liberaVagas(5);

        assertEquals(30, this.voo.getVagasAtuais());

        destruir();
    }

    @Test
    public void testeRegistraPassageiroAposReserva(){
        construir();

        Passageiro passageiro = new Passageiro("Jão", "jao@mail.com", "senha123", "4002-8922");

        this.voo.reservaVagas(passageiro, 4);

        assertEquals(26, this.voo.getVagasAtuais());
        assertEquals("Jão", this.voo.getPassageiros().get(0).getNome());
        assertEquals("jao@mail.com", this.voo.getPassageiros().get(0).getEmail());
        assertTrue(this.voo.getPassageiros().get(0).validaSenha("senha123"));
        assertEquals("4002-8922", this.voo.getPassageiros().get(0).getTel());
    }
}
