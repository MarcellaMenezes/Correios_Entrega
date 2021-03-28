package correios.Model.DAO;

import correios.Model.Conexao;
import correios.Model.Funcionario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FuncionarioDAO implements InterfaceDAO{
        SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formtDataBD = new SimpleDateFormat("yyyy-MM-dd");
        PreparedStatement psQrFunc = null, psQrCargo = null;
        ResultSet resultQrFunc = null, resulQrCargo = null;

    @Override
    public void adiciona(Object obj[], String cpf) {
        int codigoCargo = Integer.parseInt(obj[0].toString());
        String cargo = obj[1].toString();
        String localTrabalho = obj[2].toString();
        String cpff = obj[3].toString();
        String nome = obj[4].toString();
        Date dataNascimento = null;
            try {
                dataNascimento = formatData.parse(obj[5].toString());
            } catch (ParseException ex) {
                Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        char sexo = obj[5].toString().charAt(0);
        
         PreparedStatement psQrCargo;
            try {
                psQrCargo = Conexao.getConexao().prepareStatement("SELECT codCargo FROM cargo WHERE nomeCargo = ?");
                psQrCargo.setString(1, cargo);
                ResultSet resultQrCargo = psQrCargo.executeQuery();

                if (resultQrCargo.next()) {
                    System.out.println("Cargo cadastro func: "+resultQrCargo.getString("codCargo"));
                    psQrFunc = Conexao.getConexao().prepareStatement("INSERT INTO funcionario (cpf, nome, dataNascimento, sexo, fk_Cargo_codCargo, localTrabalho) VALUES"
                            + " ('" + cpff + "','" + nome
                            + "','" + formtDataBD.format(dataNascimento)
                            + "','" + sexo
                            + "'," + resultQrCargo.getString("codCargo")
                            + ", '" +localTrabalho+ "')");
                    System.out.println(psQrFunc);
                    psQrFunc.execute();
                }
            } catch (SQLException ex) {
                Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }        
    }

    @Override
    public void exclui(Object obj) {
        String cpf = (String) obj;
            try {
                psQrFunc = Conexao.getConexao().prepareStatement("DELETE FROM funcionario WHERE cpf ='"+cpf+"'");
                System.out.println(psQrFunc);
                psQrFunc.execute();
            } catch (SQLException ex) {
                Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }      
    }

    @Override
    public void altera(Object[] obj, String cpf) {
        int codigoCargo = Integer.parseInt(obj[0].toString());
        String cargo = obj[1].toString();
        String localTrabalho = obj[2].toString();
        String cpff = obj[3].toString();
        String nome = obj[4].toString();
        Date dataNascimento = null;
            try {
                dataNascimento = formatData.parse(obj[5].toString());
            } catch (ParseException ex) {
                Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        char sexo = obj[5].toString().charAt(0);
        
            try {
                //selecionando qual é o código do cargo selecionado no cbx para inserir no funcionario
                psQrCargo = Conexao.getConexao().prepareStatement("SELECT codCargo FROM cargo WHERE nomeCargo = ?");
                psQrCargo.setString(1, cargo);
                ResultSet resultQrCargo = psQrCargo.executeQuery();

                System.out.println("Cargo objeto: "+cargo);

                if (resultQrCargo.next()) {
                    psQrFunc = Conexao.getConexao().prepareStatement("UPDATE funcionario SET"
                            + " nome = '" + nome+"'"
                            + " , dataNascimento = '" + formtDataBD.format(dataNascimento)+"'"
                            + " , sexo = '" +sexo+ "'"
                            + ", fk_Cargo_codCargo = " + "'"+resultQrCargo.getString("codCargo")+"'"
                            + " WHERE cpf = '" +cpff+"'");

                    System.out.println(psQrFunc);
                    psQrFunc. executeUpdate();     
                }
            } catch (SQLException ex) {
                Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }   
    }

    @Override
    public ArrayList<Object> consulta(Object obj, String aux) {
        String localTrabalho = (String) obj;
        ArrayList resultado;
        PreparedStatement psQrFunc = null;
        ResultSet resultQrFunc = null;
        
        try {
            resultado = new ArrayList<Object>();
            psQrFunc = Conexao.getConexao().prepareStatement("SELECT f.cpf, f.nome, date_format(f.dataNascimento,'%d/%m/%Y') As dataNasc , f.sexo, f.fk_Cargo_codCargo, c.nomeCargo FROM funcionario AS f"
                + " INNER JOIN cargo AS c on f.fk_Cargo_codCargo = c.codCargo"
                + " WHERE f.localTrabalho = '"+localTrabalho+"'");
            System.out.println(psQrFunc);
            resultQrFunc = psQrFunc.executeQuery();
        
            System.out.println(psQrFunc);

            while (resultQrFunc.next()) {
                Object[] linha = {
                    resultQrFunc.getString("cpf"),
                    resultQrFunc.getString("nome"),
                    resultQrFunc.getString("dataNasc"),
                    resultQrFunc.getString("sexo"),
                    resultQrFunc.getString("nomeCargo"),
                    resultQrFunc.getString("fk_Cargo_codCargo")
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
    public ArrayList<Object> consulta(Object[] obj, String aux) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void adiciona(Object obj, String cpf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
