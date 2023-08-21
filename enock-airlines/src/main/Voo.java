package main;

public class Voo {
    public final int VAGAS_INICIAIS = 30;
    private final int MIN_VAGAS = 0;

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

    public void reservaVagas(int vagasRemovidas) throws IndexOutOfBoundsException{
        int vagasAtualizadas = this.vagasAtuais - vagasRemovidas;

        if (vagasAtualizadas < MIN_VAGAS){
            throw new IndexOutOfBoundsException("Vagas insuficientes.");
        }

        this.vagasAtuais = vagasAtualizadas;
    }

    public void liberaVagas(int vagasLiberadas) {
        int vagasAtualizadas = this.vagasAtuais + vagasLiberadas;

        if (vagasAtualizadas > this.VAGAS_INICIAIS){
            vagasAtualizadas = this.VAGAS_INICIAIS;
        }

        this.vagasAtuais = vagasAtualizadas;
    }

}
