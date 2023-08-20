package main;

public class Voo {
    public final int VAGAS_INICIAIS = 30;

    private String origem;
    private String destino;
    private String data;
    private String horario;
    private double preco;
    private int vagasAtuais;

    public Voo(String origem, String destino, String data, String horario, double preco) {
        this.origem = origem;
        this.destino = destino;
        this.data = data;
        this.horario = horario;
        this.preco = preco;
        this.vagasAtuais = this.VAGAS_INICIAIS;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getVagasAtuais(){
        return this.vagasAtuais;
    }

    public void removeVagas(int vagasRemovidas){
        this.vagasAtuais -= vagasRemovidas;
    }
}
