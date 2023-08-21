package main;

import java.util.List;

public class Reserva {
    private VooRepository vooRepository;

    public Reserva(){
        this.vooRepository = new VooRepository();
    }

    public void reservarVoo(int idVoo, int qtdVagas) {
        List<Voo> voos = this.vooRepository.getVoos();
        Voo voo = voos.get(idVoo);

        voo.reservaVagas(qtdVagas);
    }

    public VooRepository getVooRepository() {
        return this.vooRepository;
    }
}
