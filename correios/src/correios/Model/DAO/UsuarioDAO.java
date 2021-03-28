package correios.Model.DAO;
import correios.Model.Cliente;
import correios.Model.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author marce
 */
public class UsuarioDAO implements InterfaceDAO{
    Cliente cliente;

    @Override
    public void adiciona(Object obj, String cpf) {
        cliente = (Cliente) obj;
        PreparedStatement psQrCli = null;
        ResultSet resultQrCli = null;
        
        SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formtDataBD = new SimpleDateFormat("yyyy-MM-dd");
        
        //passando os dados do vetor para variaveis
        String cpff = cliente.getCpf();
        String nome = cliente.getNome();
        Date dataNascimento = cliente.getDataNascimento();
        char sexo = cliente.getSexo();
        //fim 
        
        PreparedStatement psQrLCli;
        try {
            psQrLCli = Conexao.getConexao().prepareStatement("SELECT * FROM cliente WHERE cpf = ?");
            
            psQrLCli.setString(1, cpff);
            ResultSet resultQrLCli = psQrLCli.executeQuery();
            
            System.out.println("Resultado query: "+resultQrLCli.next());
            if (resultQrLCli.next()==false){
                psQrCli = Conexao.getConexao().prepareStatement("INSERT INTO cliente (cpf, nome, dataNascimento, sexo) VALUES"
                        + " ('" + cpff
                        + "','" + nome
                        + "','" + formtDataBD.format(dataNascimento)
                        + "','" + sexo+"')");
                System.out.println(psQrCli);
                psQrCli.execute();
            }else{
                JOptionPane.showMessageDialog(null, "Já existe esse cliente cadastrado");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    @Override
    public void exclui(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Object> consulta(Object obj, String aux) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Object> consulta(Object[] obj, String aux) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void adiciona(Object[] obj, String cpf) {
        SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formtDataBD = new SimpleDateFormat("yyyy-MM-dd");
        
        String cpff = obj[0].toString();
        String nome = obj[1].toString();
        Date dataNasc = null;
        try {
            dataNasc = formatData.parse(obj[2].toString());
        } catch (ParseException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        char sexo = obj[3].toString().charAt(0);

        PreparedStatement psQrCli = null;
        ResultSet resultQrCli = null;
        
        PreparedStatement psQrLCli;
        try {
            psQrLCli = Conexao.getConexao().prepareStatement("SELECT * FROM cliente WHERE cpf = ?");
            
            psQrLCli.setString(1, cpff);
            ResultSet resultQrLCli = psQrLCli.executeQuery();
            
            System.out.println("Resultado query: "+resultQrLCli.next());
            if (resultQrLCli.next()==false){
                psQrCli = Conexao.getConexao().prepareStatement("INSERT INTO cliente (cpf, nome, dataNascimento, sexo) VALUES"
                        + " ('" + cpff
                        + "','" + nome
                        + "','" + formtDataBD.format(dataNasc)
                        + "','" + sexo+"')");
                System.out.println(psQrCli);
                psQrCli.execute();
            }else{
                JOptionPane.showMessageDialog(null, "Já existe esse cliente cadastrado");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    @Override
    public void altera(Object[] obj, String cpf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 
}
