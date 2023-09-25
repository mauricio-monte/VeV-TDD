package tests.junit5Tests;

import main.Passageiro;
import main.Voo;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VooTeste {

    private Voo voo;

    @Before
    public void setUp() {
        this.voo = new Voo("Origem A", "Destino A", "20/08/2023", "10:00", 200.0);
    }

    @Test
    public void testeReservaVagasPadrao() {
        this.voo.reservaVagas(null, 5);

        assertEquals(25, this.voo.getVagasAtuais());
    }

    @Test
    public void testeReservaVagasAbaixoDoMinimo() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            this.voo.reservaVagas(null, 35);
        });
    }

    @Test
    public void testeLiberaVagasPadrao() {
        this.voo.reservaVagas(null, 5);
        this.voo.liberaVagas(5);

        assertEquals(30, this.voo.getVagasAtuais());
    }

    @Test
    public void testeLiberaVagasAcimaDoMaximo() {
        this.voo.liberaVagas(5);

        assertEquals(30, this.voo.getVagasAtuais());
    }

    @Test
    public void testeRegistraPassageiroAposReserva() {
        Passageiro passageiro = new Passageiro("Jão", "jao@mail.com", "senha123", "4002-8922");

        this.voo.reservaVagas(passageiro, 4);

        assertEquals(26, this.voo.getVagasAtuais());
        assertEquals("Jão", this.voo.getPassageiros().get(0).getNome());
        assertEquals("jao@mail.com", this.voo.getPassageiros().get(0).getEmail());
        assertTrue(this.voo.getPassageiros().get(0).validaSenha("senha123"));
        assertEquals("4002-8922", this.voo.getPassageiros().get(0).getTel());
    }
}
