package tests;

import main.Reserva;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotEquals;

public class ReservaTeste {

    private Reserva reserva;

    public void construirReservaVazia() {
        this.reserva = new Reserva();
    }

    @AfterEach
    public void destruir(){
        this.reserva = null;
    }

    @Test
    public void testeCriarReservaVazia(){
        construirReservaVazia();

        assertNotEquals(null, this.reserva);
    }
}