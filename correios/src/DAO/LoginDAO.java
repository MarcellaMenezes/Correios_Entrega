package DAO;

import Model.Conexao;
import Model.Login;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marce
 */
public class LoginDAO implements InterfaceDAO {

    @Override
    public void adiciona(Object obj, String cpf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void exclui(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void altera(Object obj, String cpf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Object> consulta(Object obj, String aux) {
        Login login = (Login) obj;
        ArrayList resultado = null;
        
        if(aux.equals("Cliente")){
            PreparedStatement psQrCli = null;
            ResultSet resultQrCli = null;
            try {
                resultado = new ArrayList<Object>();
                //verificando primeiro se o login é de um cliente
                psQrCli = Conexao.getConexao().prepareStatement("SELECT l.codLogin, lc.fk_Cliente_cpf FROM login AS l"
                        + " INNER JOIN login_cliente AS lc on l.codLogin = lc.fk_Login_codLogin WHERE l.senha = ? AND l.email = ?");
                psQrCli.setString(1, login.getSenha());
                psQrCli.setString(2, login.getEmail());
                resultQrCli = psQrCli.executeQuery();
                
                while (resultQrCli.next()) {
                    Object[] linha = {
                        resultQrCli.getString("codLogin"),
                        resultQrCli.getString("fk_Cliente_cpf")
                    };
                    resultado.add(linha);
                }
                
                System.out.println("Query Cli: "+psQrCli);
            } catch (SQLException ex) {
                Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }else{     
            System.out.println("Entrou else p adm");
            PreparedStatement psQrAdm = null;
            ResultSet resultQrAdm = null;
            //verificando se é um adm
            try {
                resultado = new ArrayList<Object>();
                psQrAdm = Conexao.getConexao().prepareStatement("SELECT l.codLogin, c.nomeCargo FROM login as l "
                        + "INNER JOIN login_funcionario as lf on l.codLogin = lf.fk_Login_codLogin "
                        + "INNER JOIN funcionario as f on f.cpf = lf.fk_Funcionario_cpf "
                        + "INNER JOIN cargo as c on f.fk_Cargo_codCargo = c.codCargo"
                        + " WHERE l.senha = ? AND l.email = ?");
                psQrAdm.setString(1, login.getSenha());
                psQrAdm.setString(2, login.getEmail());
                resultQrAdm = psQrAdm.executeQuery();
                
                 while(resultQrAdm.next()) {
                    Object[] linha = {
                        resultQrAdm.getString("codLogin"),
                        resultQrAdm.getString("nomeCargo")
                    };
                    resultado.add(linha);
                }
                System.out.println("Query Adm: "+psQrAdm);
                
            } catch (SQLException ex) {
                Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
            }          
        }
        return resultado;
    }
    
}
