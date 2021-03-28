/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correios.Controller;

import correios.GUI.ViewCadastroFuncAgencia;
import correios.GUI.ViewCadastroFuncCEE;
import correios.GUI.ViewCadastroFuncCarga;
import correios.GUI.ViewHomeFuncionario;
import correios.Model.DAO.FuncionarioDAO;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class ControllerFuncionario {
    FuncionarioDAO funcDao;

    public ControllerFuncionario() {
        funcDao = new FuncionarioDAO();  
    }
    
    public  ArrayList<Object> consulta(Object obj, String aux){
        return funcDao.consulta(obj, aux);     
    }
    
    public void altera(Object[] obj, String cpf){
       funcDao.altera(obj, cpf);
    }
    
    public void adiciona(Object obj[], String cpf){
       funcDao.adiciona(obj, cpf);
    }
    
    public void exclui(Object obj) {
       funcDao.exclui(obj);
    }
    
    public void abrirViewHomeFunc(){
        ViewHomeFuncionario vHomeFunc = new ViewHomeFuncionario();
        vHomeFunc.setVisible(true);
    }
    
    public void abrirViewCadastroFuncAgencia() throws ParseException, SQLException{
        ViewCadastroFuncAgencia vAgencia = new ViewCadastroFuncAgencia();
        vAgencia.setVisible(true);
    }
    
    public void abrirViewCadastroFuncCEE() throws ParseException, SQLException{
        ViewCadastroFuncCEE vCEE = new ViewCadastroFuncCEE();
        vCEE.setVisible(true);
    }
    
    public void abrirViewCadastroFuncCarga() throws ParseException, SQLException{
        ViewCadastroFuncCarga vCarga = new ViewCadastroFuncCarga();
        vCarga.setVisible(true);
    }
    
}
