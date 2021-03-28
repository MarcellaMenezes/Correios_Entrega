package correios.Model;

import correios.Controller.ControllerEndereco;
import correios.Controller.ControllerLogin;
import java.util.Date;
import java.util.List;

public class Cliente extends Usuario{

private List<Object[]> enderecos;
private Object login;

    public Cliente(String cpf, String nome, Date dataNascimento, char sexo, List<Object[]> enderecos, Object login) {
        super(cpf, nome, dataNascimento, sexo);
        this.enderecos = enderecos;
        this.login = login;
    }  

    public List<Object[]> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Object[]> enderecos) {
        this.enderecos = enderecos;
    }

    public Object getLogin() {
        return login;
    }

    public void setLogin(Object login) {
        this.login = login;
    }

    @Override
    public void imprimir(){
        super.imprimir();
        
        ControllerEndereco enderecoController = new ControllerEndereco();
        ControllerLogin loginController = new ControllerLogin();
        loginController.imprimirLogin();
        
        System.out.println("Endereços: ");
        for(int i=0; i<enderecos.size(); i++){
            enderecoController.imprimirEnderecos();
        }
    } 
}
