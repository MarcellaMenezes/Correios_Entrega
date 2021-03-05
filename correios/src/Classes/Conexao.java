package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao {
    private static Connection conexao = null;
    
    private Conexao(){
        try{
           //versão 5.6 do mysql
           //tentar estabelecer conexao
          conexao = DriverManager.getConnection(
                  "jdbc:mysql://localhost:3306/correios_entrega", //linha de conexao
                  "root", //usuario do mysql
                  ""  // senha do mysql);
            );

        } catch (SQLException e){
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, e);    
        } 
    }
    
    public static Connection getConexao(){
        if(conexao == null)
            new Conexao();
        return conexao;
    }    
}
