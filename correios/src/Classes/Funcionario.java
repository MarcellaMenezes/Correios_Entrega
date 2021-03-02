package Classes;

import java.util.Date;

public abstract class Funcionario extends Usuario {
    private int codigo;
    private String cargo;
    
    public Funcionario(String cpf, String nome, Date dataNascimento, char sexo, int codigo, String cargo) {
        super(cpf, nome, dataNascimento, sexo);
        this.codigo = codigo;
        this.cargo = cargo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
    
}
