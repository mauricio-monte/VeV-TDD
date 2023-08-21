package tests;

import main.Reserva;
import main.Voo;
import main.VooRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
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
        assertNotEquals(null, this.reserva.getVooRepository());
    }

    @Test
    public void reservar1VooCom1Passageiro(){
        construirReservaVazia();

        this.reserva.reservarVoo(0, 1);

        VooRepository voos = this.reserva.getVoos();
        Voo vooReservado = voos.getVooPorId(0);
        assertEquals(29, vooReservado.getVagasAtuais());
    }
}