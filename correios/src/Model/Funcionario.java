package Model;

import java.util.Date;

public abstract class Funcionario extends Usuario {
    private int codigoCargo;
    private String cargo;
    private String localTrabalho;

    public Funcionario(int codigoCargo, String cargo, String localTrabalho, String cpf, String nome, Date dataNascimento, char sexo) {
        super(cpf, nome, dataNascimento, sexo);
        this.codigoCargo = codigoCargo;
        this.cargo = cargo;
        this.localTrabalho = localTrabalho;
    }

    public int getCodigoCargo() {
        return codigoCargo;
    }

    public void setCodigoCargo(int codigoCargo) {
        this.codigoCargo = codigoCargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getLocalTrabalho() {
        return localTrabalho;
    }

    public void setLocalTrabalho(String localTrabalho) {
        this.localTrabalho = localTrabalho;
    }
    
   
}
