/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Conexao;
import Model.Endereco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author marce
 */
public class EnderecoDAO implements InterfaceDAO {

    @Override
    public void adiciona(Object obj, String cpf) {
        Endereco endereco = (Endereco) obj;
        if (endereco != null ) {
            PreparedStatement psQrE;
            try {
                psQrE = Conexao.getConexao().prepareStatement("INSERT INTO endereco ( identificacao, pais, cep, rua, numero, complemento, bairro, cidade, uf) VALUES"
                        + " ('" + endereco.getIdentificacaoEndereco()
                        + "','" + endereco.getPais()
                        + "','" + endereco.getCep()
                        + "','" + endereco.getRua()
                        + "','" + endereco.getNumero()
                        + "','" + endereco.getComplemento()
                        + "','" + endereco.getBairro()
                        + "','" + endereco.getCidade()
                        + "','" + endereco.getUf()+"')");
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
                psQrEnd.setString(1, endereco.getIdentificacaoEndereco());
                psQrEnd.setString(2, endereco.getPais() );
                psQrEnd.setString(3, endereco.getCep() );
                psQrEnd.setString(4, endereco.getRua());
                psQrEnd.setInt(5, endereco.getNumero());
                psQrEnd.setString(6, endereco.getComplemento());
                psQrEnd.setString(7, endereco.getBairro());
                psQrEnd.setString(8, endereco.getCidade());
                psQrEnd.setString(9, endereco.getUf());
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
    public void altera(Object obj, String cpf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
