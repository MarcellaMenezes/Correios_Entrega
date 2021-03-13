package Model;

import java.util.Date;

public abstract class Funcionario extends Usuario {
    private int codigoCargo;
    private String cargo;
    
    public Funcionario(String cpf, String nome, Date dataNascimento, char sexo, int codigoCargo, String cargo) {
        super(cpf, nome, dataNascimento, sexo);
        this.codigoCargo = codigoCargo;
        this.cargo = cargo;
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
    
    
}
