package tests;

import main.Reserva;
import main.Voo;
import main.VooRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ReservaTeste {

    private Reserva reserva;

    public void construirReservaVazia() {
        this.reserva = new Reserva();
    }

    public void construirReservaCom3Voos(){
        construirReservaVazia();

        Voo voo1 = new Voo("Origem A", "Destino A", "20/08/2023", "10:00", 200.0);
        Voo voo2 = new Voo("Origem B", "Destino B", "20/08/2023", "10:00", 200.0);
        Voo voo3 = new Voo("Origem C", "Destino C", "20/08/2023", "10:00", 200.0);

        this.reserva.getVooRepository().adicionarVoo(voo1);
        this.reserva.getVooRepository().adicionarVoo(voo2);
        this.reserva.getVooRepository().adicionarVoo(voo3);
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
    public void testeReservar1VooCom1Passageiro(){
        construirReservaCom3Voos();

        this.reserva.reservarVoo(0, 1);

        VooRepository vooRepository = this.reserva.getVooRepository();
        List<Voo> voos = vooRepository.getVoos();
        Voo vooReservado = voos.get(0);

        assertEquals(29, vooReservado.getVagasAtuais());
    }

}