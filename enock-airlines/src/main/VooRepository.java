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
                    "ID: %d | Origem: %s | Destino: %s | Data: %s | Horário: %s | Preço: R$ %.2f | Vagas: %d\n",
                    i, voo.getOrigem(), voo.getDestino(), voo.getData(), voo.getHorario(), voo.getPreco(),
                    voo.getVagasAtuais()
            );
        }

        return lista;
    }

    public String getStringVooPorDestino(String destino) {
        String lista = "";

        for (int i = 0; i < this.voos.size(); i++){
            Voo voo = this.voos.get(i);
            if (voo.getDestino().equals(destino)) {
                lista += String.format(
                        "ID: %d | Origem: %s | Destino: %s | Data: %s | Horário: %s | Preço: R$ %.2f | Vagas: %d\n",
                        i, voo.getOrigem(), voo.getDestino(), voo.getData(), voo.getHorario(), voo.getPreco(),
                        voo.getVagasAtuais()
                );
            }
        }

        return lista;
    }

    public String getStringVooPorOrigem(String origem) {
        String lista = "";

        for (int i = 0; i < this.voos.size(); i++){
            Voo voo = this.voos.get(i);
            if (voo.getOrigem().equals(origem)) {
                lista += String.format(
                        "ID: %d | Origem: %s | Destino: %s | Data: %s | Horário: %s | Preço: R$ %.2f | Vagas: %d\n",
                        i, voo.getOrigem(), voo.getDestino(), voo.getData(), voo.getHorario(), voo.getPreco(),
                        voo.getVagasAtuais()
                );
            }
        }

        return lista;
    }

    public String getStringVooPorData(String data) {
        String lista = "";

        for (int i = 0; i < this.voos.size(); i++){
            Voo voo = this.voos.get(i);
            if (voo.getData().equals(data)) {
                lista += String.format(
                        "ID: %d | Origem: %s | Destino: %s | Data: %s | Horário: %s | Preço: R$ %.2f | Vagas: %d\n",
                        i, voo.getOrigem(), voo.getDestino(), voo.getData(), voo.getHorario(), voo.getPreco(),
                        voo.getVagasAtuais()
                );
            }
        }

        return lista;
    }

    public String getStringVooPorId(int idVoo) {
        String lista;

        Voo vooEscolhido = this.voos.get(idVoo);

        lista = String.format(
                "ID: %d | Origem: %s | Destino: %s | Data: %s | Horário: %s | Preço: R$ %.2f | Vagas: %d\n",
                idVoo, vooEscolhido.getOrigem(), vooEscolhido.getDestino(), vooEscolhido.getData(),
                vooEscolhido.getHorario(), vooEscolhido.getPreco(), vooEscolhido.getVagasAtuais()
        );

        return lista;
    }

    public String getStringVooPorVagasMinimas(int minVagas) {
        String lista = "";

        for (int i = 0; i < this.voos.size(); i++){
            Voo voo = this.voos.get(i);
            if (voo.getVagasAtuais() >= minVagas) {
                lista += String.format(
                        "ID: %d | Origem: %s | Destino: %s | Data: %s | Horário: %s | Preço: R$ %.2f | Vagas: %d\n",
                        i, voo.getOrigem(), voo.getDestino(), voo.getData(), voo.getHorario(), voo.getPreco(),
                        voo.getVagasAtuais()
                );
            }
        }

        return lista;
    }

    public String getStringVooPorPreco(double v) {
        return "";
    }

    public String getStringVooPorHorario(String s, String s1) {
        return "";
    }
}
