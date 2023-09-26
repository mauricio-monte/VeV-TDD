package junit5Tests;

import main.Passageiro;
import main.Reserva;
import main.Voo;
import main.VooRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReservaTeste {

    private Reserva reserva;

    public void construirReservaVazia() {
        this.reserva = new Reserva();
    }

    public void construirReservaCom3Voos() {
        construirReservaVazia();

        Voo voo1 = new Voo("Origem A", "Destino A", "20/08/2023", "10:00", 200.0);
        Voo voo2 = new Voo("Origem B", "Destino B", "20/08/2023", "10:00", 200.0);
        Voo voo3 = new Voo("Origem C", "Destino C", "20/08/2023", "10:00", 200.0);

        this.reserva.getVooRepository().adicionarVoo(voo1);
        this.reserva.getVooRepository().adicionarVoo(voo2);
        this.reserva.getVooRepository().adicionarVoo(voo3);
    }

    @AfterEach
    public void destruir() {
        this.reserva = null;
    }

    @Test
    public void CT1Reservar1Vaga() {
        construirReservaCom3Voos();

        Passageiro passageiro = new Passageiro("Jão", "jao@mail.com", "senha123", "4002-8922");

        this.reserva.reservarVoo(0, 1, passageiro);

        VooRepository vooRepository = this.reserva.getVooRepository();
        List<Voo> voos = vooRepository.getVoos();
        Voo vooReservado = voos.get(0);

        assertEquals(29, vooReservado.getVagasAtuais());
    }

    @Test
    public void CT2Reservar2Vagas() {
        construirReservaCom3Voos();

        Passageiro passageiro = new Passageiro("Jão", "jao@mail.com", "senha123", "4002-8922");

        this.reserva.reservarVoo(0, 2, passageiro);

        VooRepository vooRepository = this.reserva.getVooRepository();
        List<Voo> voos = vooRepository.getVoos();
        Voo vooReservado = voos.get(0);

        assertEquals(28, vooReservado.getVagasAtuais());
    }

    @Test
    public void CT3Reservar15Vagas() {
        construirReservaCom3Voos();

        Passageiro passageiro = new Passageiro("Jão", "jao@mail.com", "senha123", "4002-8922");

        this.reserva.reservarVoo(0, 15, passageiro);

        VooRepository vooRepository = this.reserva.getVooRepository();
        List<Voo> voos = vooRepository.getVoos();
        Voo vooReservado = voos.get(0);

        assertEquals(15, vooReservado.getVagasAtuais());
    }

    @Test
    public void CT4Reservar29Vagas() {
        construirReservaCom3Voos();

        Passageiro passageiro = new Passageiro("Jão", "jao@mail.com", "senha123", "4002-8922");

        this.reserva.reservarVoo(0, 29, passageiro);

        VooRepository vooRepository = this.reserva.getVooRepository();
        List<Voo> voos = vooRepository.getVoos();
        Voo vooReservado = voos.get(0);

        assertEquals(1, vooReservado.getVagasAtuais());
    }

    @Test
    public void CT5Reservar30Vagas() {
        construirReservaCom3Voos();

        Passageiro passageiro = new Passageiro("Jão", "jao@mail.com", "senha123", "4002-8922");

        this.reserva.reservarVoo(0, 30, passageiro);

        VooRepository vooRepository = this.reserva.getVooRepository();
        List<Voo> voos = vooRepository.getVoos();
        Voo vooReservado = voos.get(0);

        assertEquals(0, vooReservado.getVagasAtuais());
    }

    @Test
    public void CT6Liberar1Vaga() {
        construirReservaCom3Voos();

        Passageiro passageiro = new Passageiro("Jão", "jao@mail.com", "senha123", "4002-8922");

        this.reserva.reservarVoo(1, 5, passageiro);

        this.reserva.cancelarReserva(1, 1, "jao@mail.com", "senha123");

        assertEquals(26, this.reserva.getVooRepository().getVoos().get(1).getVagasAtuais());

    }

    @Test
    public void CT7Liberar2Vagas() {
        construirReservaCom3Voos();

        Passageiro passageiro = new Passageiro("Jão", "jao@mail.com", "senha123", "4002-8922");

        this.reserva.reservarVoo(1, 5, passageiro);

        this.reserva.cancelarReserva(1, 2, "jao@mail.com", "senha123");

        assertEquals(27, this.reserva.getVooRepository().getVoos().get(1).getVagasAtuais());

    }

    @Test
    public void CT8Liberar15Vagas() {
        construirReservaCom3Voos();

        Passageiro passageiro = new Passageiro("Jão", "jao@mail.com", "senha123", "4002-8922");

        this.reserva.reservarVoo(1, 20, passageiro);

        this.reserva.cancelarReserva(1, 15, "jao@mail.com", "senha123");

        assertEquals(25, this.reserva.getVooRepository().getVoos().get(1).getVagasAtuais());

    }

    @Test
    public void CT9Liberar29Vagas() {
        construirReservaCom3Voos();

        Passageiro passageiro = new Passageiro("Jão", "jao@mail.com", "senha123", "4002-8922");

        this.reserva.reservarVoo(1, 30, passageiro);

        this.reserva.cancelarReserva(1, 29, "jao@mail.com", "senha123");

        assertEquals(29, this.reserva.getVooRepository().getVoos().get(1).getVagasAtuais());

    }

    @Test
    public void CT10Liberar30Vagas() {
        construirReservaCom3Voos();

        Passageiro passageiro = new Passageiro("Jão", "jao@mail.com", "senha123", "4002-8922");

        this.reserva.reservarVoo(1, 30, passageiro);

        this.reserva.cancelarReserva(1, 30, "jao@mail.com", "senha123");

        assertEquals(30, this.reserva.getVooRepository().getVoos().get(1).getVagasAtuais());


    }

    @Test
    public void CT11CancelarReservaSenhaInvalida() {
        construirReservaCom3Voos();

        Passageiro passageiro = new Passageiro("Jão", "jao@mail.com", "senha123", "4002-8922");
        this.reserva.reservarVoo(1, 5, passageiro);

        assertThrows(SecurityException.class, () -> {
            this.reserva.cancelarReserva(1, 1, "jao@mail.com", "senha_errada");
        });

        assertEquals(25, this.reserva.getVooRepository().getVoos().get(1).getVagasAtuais());
    }
}