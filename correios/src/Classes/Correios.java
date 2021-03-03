package Classes;

import GUI.ViewLogin;
import java.sql.Connection;

public class Correios {

    public static void main(String[] args) {
       Connection conexao = Conexao.getConexao();
       ViewLogin viewLogin = new ViewLogin();
       viewLogin.setVisible(true);
    }
    
}
