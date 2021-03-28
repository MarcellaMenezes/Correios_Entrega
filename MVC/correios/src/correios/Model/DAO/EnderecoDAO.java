package correios.Model.DAO;

import correios.Model.Conexao;
import correios.Model.Endereco;
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
public class EnderecoDAO implements InterfaceDAO {
    Endereco endereco;

    @Override
    public void adiciona(Object obj, String cpf) {  
    }

    @Override
    public ArrayList<Object> consulta(Object obj, String aux) {
        String cpf = (String) obj;
        ArrayList resultado;
        PreparedStatement psQrEnd = null;
        ResultSet resultQrEnd = null;
        System.out.println("Cpf no metodos: "+cpf);
        
        try {
            resultado = new ArrayList<Object>();
            psQrEnd = Conexao.getConexao().prepareStatement("SELECT * FROM endereco AS e "
                    + " INNER JOIN endereco_cliente AS ec on e.codEndereco = ec.fk_Endereco_codEndereco"
                    + " INNER JOIN cliente AS c on c.cpf = ec.fk_Cliente_cpf"
                    + " WHERE c.cpf = '"+cpf+"'");
            resultQrEnd = psQrEnd.executeQuery();
        
            System.out.println(psQrEnd);

            while (resultQrEnd.next()) {
                Object[] linha = {
                    resultQrEnd.getString("identificacao"),
                    resultQrEnd.getString("pais"),
                    resultQrEnd.getString("cep"),
                    resultQrEnd.getString("rua"),
                    resultQrEnd.getString("numero"),
                    resultQrEnd.getString("complemento"),
                    resultQrEnd.getString("bairro"),
                    resultQrEnd.getString("cidade"),
                    resultQrEnd.getString("uf")
                };
                resultado.add(linha);
            }
            return resultado;
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return null;      
    }

    @Override
    public void exclui(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Object> consulta(Object[] obj, String aux) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void adiciona(Object[] obj, String cpf) {
        String identificacao =  obj[0].toString();
        String pais = obj[1].toString();
        String cep = obj[2].toString();
        String rua = obj[3].toString();
        int numero = Integer.parseInt(obj[4].toString());
        String complemento = obj[5].toString();
        String bairro = obj[6].toString();
        String cidade = obj[7].toString();
        String uf = obj[8].toString();
        
        endereco = new Endereco(identificacao, pais, cep, rua, numero, complemento, bairro, cidade, uf);
        
        if (obj != null ) {
            PreparedStatement psQrE;
            try {
                psQrE = Conexao.getConexao().prepareStatement("INSERT INTO endereco ( identificacao, pais, cep, rua, numero, complemento, bairro, cidade, uf) VALUES"
                        + " ('" + identificacao
                        + "','" + pais
                        + "','" + cep
                        + "','" + rua
                        + "','" + numero
                        + "','" + complemento
                        + "','" + bairro
                        + "','" + cidade
                        + "','" + uf+"')");
                System.out.println(psQrE);
                psQrE.execute();  
                
            } catch (SQLException ex) {
                Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
      
            //pegando o codEndereco do Endereço inserido anteriomente para cadastrar na tabela endereco_cliente
            PreparedStatement psQrEnd;
            try {
                psQrEnd = Conexao.getConexao().prepareStatement("SELECT codEndereco FROM endereco WHERE "
                        + "identificacao = ? and pais = ? and cep = ? and rua = ? and numero = ? and complemento = ? and bairro = ? and cidade = ? and uf = ?");
                psQrEnd.setString(1, identificacao);
                psQrEnd.setString(2, pais);
                psQrEnd.setString(3, cep);
                psQrEnd.setString(4, rua);
                psQrEnd.setInt(5, numero);
                psQrEnd.setString(6, complemento);
                psQrEnd.setString(7, bairro);
                psQrEnd.setString(8, cidade);
                psQrEnd.setString(9, uf);
                ResultSet resultQrEnd = psQrEnd.executeQuery();

                if (resultQrEnd.next()) {
                    PreparedStatement psQrEC = Conexao.getConexao().prepareStatement("INSERT INTO endereco_cliente (fk_Endereco_codEndereco, fk_Cliente_cpf) VALUES"
                             + " ('" +resultQrEnd.getString("codEndereco")
                             + "','" + cpf+"')");
                     System.out.println(psQrEC);
                     psQrEC.execute();
            }
            } catch (SQLException ex) {
                Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }

    @Override
    public void altera(Object[] obj, String cpf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
