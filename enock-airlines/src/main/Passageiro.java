package main;

public class Passageiro {
    String nome;
    String email;
    String senha;
    String tel;

    public Passageiro(String nome, String email, String senha, String tel) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tel = tel;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean validaSenha(String senha) {
        return this.senha.equals(senha);
    }

    public void setSenha(String senha){
        this.senha = senha;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

}
