package tests.junit5Tests;

import main.Passageiro;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PassageiroTeste {
    private Passageiro passageiro;

    @Before
    public void setUp() {
        this.passageiro = new Passageiro("Jão", "jao@mail.com", "senha123", "4002-8922");
    }

    @Test
    public void testeAdcPassageiro() {
        assertEquals("Jão", this.passageiro.getNome());
        assertEquals("jao@mail.com", this.passageiro.getEmail());
        assertTrue(this.passageiro.validaSenha("senha123"));
        assertEquals("4002-8922", this.passageiro.getTel());
    }

    @Test
    public void testeExcecaoSenhaInvalida() {
        assertFalse(this.passageiro.validaSenha("senha_errada"));
    }

    @Test
    public void testeExcecaoNomeNulo() {
        assertThrows(NullPointerException.class, () -> {
            this.passageiro.setNome(null);
        });
    }

    @Test
    public void testeExcecaoEmailNulo() {
        assertThrows(NullPointerException.class, () -> {
            this.passageiro.setEmail(null);
        });
    }

    @Test
    public void testeExcecaoSenhaNula() {
        assertThrows(NullPointerException.class, () -> {
            this.passageiro.setSenha(null);
        });
    }

    @Test
    public void testeExcecaoTelNulo() {
        assertThrows(NullPointerException.class, () -> {
            this.passageiro.setTel(null);
        });
    }
}
