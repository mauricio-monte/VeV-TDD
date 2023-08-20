package tests;
import main.Passageiro;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class PassageiroTeste {
    private Passageiro passageiro;

    @Test
    public void testePassageiroNulo(){
        assertEquals(null, this.passageiro);
    }

    @Test
    public void testeAdcPassageiro(){
        this.passageiro = new Passageiro("Jão", "jao@mail.com", "senha123", "4002-8922");
        assertEquals("Jão", this.passageiro.getNome());
        assertEquals("jao@mail.com", this.passageiro.getEmail());
        assertTrue(this.passageiro.validaSenha("senha123"));
        assertEquals("4002-8922", this.passageiro.getTel());
    }
}
