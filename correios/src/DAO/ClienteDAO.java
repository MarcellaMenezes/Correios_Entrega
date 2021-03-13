package DAO;

import Model.Cliente;
import Model.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author marce
 */
public class ClienteDAO implements InterfaceDAO{

    @Override
    public void adiciona(Object obj, String cpf) {
        PreparedStatement psQrCli = null;
        ResultSet resultQrCli = null;
        
        SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formtDataBD = new SimpleDateFormat("yyyy-MM-dd");
        
        Cliente usuario = (Cliente) obj;
        System.out.println("Objeto Usuario existe");
            PreparedStatement psQrLCli;
        try {
            psQrLCli = Conexao.getConexao().prepareStatement("SELECT * FROM cliente WHERE cpf = ?");
            
            psQrLCli.setString(1, usuario.getCpf());
            ResultSet resultQrLCli = psQrLCli.executeQuery();
            
            System.out.println("Resultado query: "+resultQrLCli.next());
            if (resultQrLCli.next()==false){
                 System.out.println("Entrou para cadastrar");
                psQrCli = Conexao.getConexao().prepareStatement("INSERT INTO cliente (cpf, nome, dataNascimento, sexo) VALUES"
                        + " ('" + usuario.getCpf()
                        + "','" + usuario.getNome()
                        + "','" + formtDataBD.format(usuario.getDataNascimento())
                        + "','" + usuario.getSexo()+"')");
                System.out.println(psQrCli);
                psQrCli.execute();
            }else{
                JOptionPane.showMessageDialog(null, "Já existe esse cliente cadastrado");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
            
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
