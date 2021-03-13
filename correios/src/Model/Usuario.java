package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Usuario {
    private String cpf;
    private String nome;
    private Date dataNascimento;
    private char sexo;

    public Usuario(String cpf, String nome, Date dataNascimento, char sexo) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }
    
     public void imprimir(){
        SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyy");
        System.out.println("CPF: "+cpf+"\nNome: "+nome+"\nData de Nascimento: "+formatData.format(dataNascimento)+"\nSexo: "+sexo);
     }
}
