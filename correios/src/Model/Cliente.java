package Model;

import java.util.Date;
import java.util.List;

public class Cliente extends Usuario{

private List<Endereco> enderecos;
private Login login;

    public Cliente(String cpf, String nome, Date dataNascimento, char sexo, List<Endereco> enderecos, Login login) {
        super(cpf, nome, dataNascimento, sexo);
        this.enderecos = enderecos;
        this.login = login;
    }  

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
    
    @Override
    public void imprimir(){
        super.imprimir();
        login.imprimir();
        System.out.println("Endereços: ");
        for(int i=0; i<enderecos.size(); i++){
            enderecos.get(i).imprimir();
        }
    } 
}
