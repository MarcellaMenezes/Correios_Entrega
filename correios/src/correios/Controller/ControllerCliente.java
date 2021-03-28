
package correios.Controller;

import correios.Model.DAO.UsuarioDAO;
import java.text.ParseException;
/**
 *
 * @author marce
 */
public class ControllerCliente {
    UsuarioDAO daoUsuario;

    public ControllerCliente() {
        daoUsuario = new UsuarioDAO();
    }
    
    public void cadastrarCliente(Object[] cliente, String cpf) throws ParseException{
        daoUsuario.adiciona(cliente, cpf);
    }   
}
