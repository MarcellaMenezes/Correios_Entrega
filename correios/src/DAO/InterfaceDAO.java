package DAO;

import java.util.ArrayList;


public interface InterfaceDAO {
    
    public void adiciona(Object obj, String cpf);
    public void exclui(Object obj);
    public void altera(Object obj, String cpf);
    public ArrayList<Object> consulta(Object obj, String aux);
    
}
