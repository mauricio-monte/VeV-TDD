package main;

import java.util.ArrayList;
import java.util.List;

public class VooRepository {
    private List<Voo> voos;

    public VooRepository(){
        this.voos = new ArrayList<>();
    }

    public void adicionarVoo(Voo voo) {
        this.voos.add(voo);
    }

    public List<Voo> getVoos() {
        return this.voos;
    }

    public String listarVoos(){
        String lista = "";

        for (int i = 0; i < this.voos.size(); i++){
            Voo voo = this.voos.get(i);
            lista += String.format(
                    "ID: %d | Destino: %s | Data: %s | Horário: %s | Preço: R$ %.2f\n",
                    i, voo.getDestino(), voo.getData(), voo.getHorario(), voo.getPreco()
            );
        }

        return lista;
    }
}
