package main;

import java.util.List;

public class Reserva {
    private VooRepository vooRepository;

    public Reserva(){
        this.vooRepository = new VooRepository();
    }

    public void reservarVoo(int idVoo, int qtdVagas, Passageiro passageiro) {
        List<Voo> voos = this.vooRepository.getVoos();
        Voo voo = voos.get(idVoo);

        voo.reservaVagas(passageiro, qtdVagas);
    }

    public VooRepository getVooRepository() {
        return this.vooRepository;
    }


    public void cancelarReserva(int idVoo, int qtdVagas, String email, String senha) {
        Voo voo = this.vooRepository.getVoos().get(idVoo);

        Passageiro passageiro = voo.getPassageiroPorEmail(email);

        if (!passageiro.validaSenha(senha)){
            throw new SecurityException("Senha inválida!");
        }

        voo.liberaVagas(qtdVagas);
    }


}
