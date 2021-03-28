/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correios.Controller;

import correios.GUI.ViewCadastro;
import correios.GUI.ViewHomeCliente;
import correios.GUI.ViewHomeFuncionario;
import correios.Model.DAO.LoginDAO;
import correios.Model.Login;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author marce
 */
public class ControllerLogin {
    Login login;
    LoginDAO daoLogin;
 

    public ControllerLogin() {
        daoLogin = new LoginDAO();     
    }
    
    public void imprimirLogin(){
        login.imprimir();
    }
    
    public void adiciona(Object[] obj, String cpf){
        daoLogin.adiciona(obj, cpf);
    }
    
    public void abrirViewHomeCliente(String cpf){
        ViewHomeCliente vHome = null;
        vHome = new ViewHomeCliente(cpf);
        vHome.setVisible(true);
    }
    
    public void abrirViewHomeFuncionario(){
        ViewHomeFuncionario vHomeFunc = new ViewHomeFuncionario();
        vHomeFunc.setVisible(true);
    }
    
    public void abrirViewCadastroLogin() throws ParseException{
        ViewCadastro cadastro = new ViewCadastro();
        cadastro.setVisible(true);
    }
    

    public ArrayList<Object[]> buscarLogin_DAO(Object[] obj, String home){
        String email = obj[0].toString();
        String senha = obj[1].toString();
        login = new Login(email, senha); 
        
        ArrayList<Object> arrayObj = daoLogin.consulta(login,home);
        ArrayList<Object[]> arrayObjLogins = new ArrayList<>();

        for(Object login: arrayObj){
            arrayObjLogins.add((Object[])login);
        }    
        return arrayObjLogins; 
    }  
}
