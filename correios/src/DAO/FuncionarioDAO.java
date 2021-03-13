package DAO;

import Model.Conexao;
import Model.Funcionario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FuncionarioDAO implements InterfaceDAO{
        SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formtDataBD = new SimpleDateFormat("yyyy-MM-dd");
        PreparedStatement psQrFunc = null, psQrCargo = null;
        ResultSet resultQrFunc = null, resulQrCargo = null;

    @Override
    public void adiciona(Object obj, String cpf) {
        Funcionario funcionario = (Funcionario) obj;
         PreparedStatement psQrCargo;
            try {
                psQrCargo = Conexao.getConexao().prepareStatement("SELECT codCargo FROM cargo WHERE nomeCargo = ?");
                psQrCargo.setString(1, funcionario.getCargo());
                ResultSet resultQrCargo = psQrCargo.executeQuery();

                if (resultQrCargo.next()) {
                    System.out.println("Cargo cadastro func: "+resultQrCargo.getString("codCargo"));
                    psQrFunc = Conexao.getConexao().prepareStatement("INSERT INTO funcionario (cpf, nome, dataNascimento, sexo, fk_Cargo_codCargo) VALUES"
                            + " ('" + funcionario.getCpf() + "','" + funcionario.getNome()
                            + "','" + formtDataBD.format(funcionario.getDataNascimento())
                            + "','" + funcionario.getSexo()
                            + "'," + resultQrCargo.getString("codCargo") + ")");
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
    public void altera(Object obj, String cpf) {
        Funcionario funcionario = (Funcionario) obj;
            try {
                //selecionando qual é o código do cargo selecionado no cbx para inserir no funcionario
                psQrCargo = Conexao.getConexao().prepareStatement("SELECT codCargo FROM cargo WHERE nomeCargo = ?");
                psQrCargo.setString(1, funcionario.getCargo());
                ResultSet resultQrCargo = psQrCargo.executeQuery();

                System.out.println("Cargo objeto: "+funcionario.getCargo());

                if (resultQrCargo.next()) {
                    //String sexo = Character.toString(funcionario.getSexo());
                    psQrFunc = Conexao.getConexao().prepareStatement("UPDATE funcionario SET"
                            + " nome = '" + funcionario.getNome()+"'"
                            + " , dataNascimento = '" + formtDataBD.format(funcionario.getDataNascimento())+"'"
                            + " , sexo = '" +funcionario.getSexo() + "'"
                            + ", fk_Cargo_codCargo = " + "'"+resultQrCargo.getString("codCargo")+"'"
                            + " WHERE cpf = '" +funcionario.getCpf()+"'");

                    System.out.println(psQrFunc);
                    psQrFunc. executeUpdate();     
                }
            } catch (SQLException ex) {
                Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }   
    }

    @Override
    public ArrayList<Object> consulta(Object obj, String aux) {
        String cargo = (String) obj;
        ArrayList resultado;
        PreparedStatement psQrFunc = null;
        ResultSet resultQrFunc = null;
        
        try {
            resultado = new ArrayList<Object>();
            psQrFunc = Conexao.getConexao().prepareStatement("SELECT f.cpf, f.nome, date_format(f.dataNascimento,'%d/%m/%Y') As dataNasc , f.sexo, f.fk_Cargo_codCargo, c.nomeCargo FROM funcionario AS f"
                + " INNER JOIN cargo AS c on f.fk_Cargo_codCargo = c.codCargo");
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
    
}
