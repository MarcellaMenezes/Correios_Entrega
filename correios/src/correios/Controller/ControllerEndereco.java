package correios.Controller;

import correios.Model.DAO.EnderecoDAO;
import correios.GUI.ViewCadastroEndereco;
import correios.GUI.ViewEndereco;
import correios.GUI.ViewHomeCliente;
import correios.Model.Endereco;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class ControllerEndereco {
    private Endereco endereco;
    private EnderecoDAO daoEndereco;

    public ControllerEndereco() {
        daoEndereco = new EnderecoDAO();    
    }
    
    public void imprimirEnderecos(){
        endereco.imprimir();
    }
    
    public void cadastraEndereco(Object[] endereco, String cpf){
        daoEndereco.adiciona(endereco, cpf);
    }
    public void abrirViewEndereco(String cpfCli) throws SQLException{
      
        ViewEndereco endView = new ViewEndereco(cpfCli, this);
        endView.setVisible(true);
        endView.setLocationRelativeTo(null);
    } 
    
    public void abrirViewCadastroEndereco(String cpfCli) throws SQLException, ParseException{
      
        ViewCadastroEndereco endCadView = new ViewCadastroEndereco(cpfCli, this);
        endCadView.setVisible(true);
        endCadView.setLocationRelativeTo(null);
    } 
     
    public void abrirViewHome(String cpfCli){
        ViewHomeCliente viewCliente = new ViewHomeCliente(cpfCli);
        viewCliente.setVisible(true);
        viewCliente.setLocationRelativeTo(null);
    }
    
    public ArrayList<Object[]> buscarEndereco_DAO(Object obj){
        String cpf = (String) obj;
        
        ArrayList<Object> arrayObj = daoEndereco.consulta(obj, null);
        ArrayList<Object[]> arrayObjEnderecos = new ArrayList<>();
        for(Object endereco: arrayObj){
            arrayObjEnderecos.add((Object[]) endereco);                       
        }
        return arrayObjEnderecos;
    }
    
    
    
    
   
    
}
