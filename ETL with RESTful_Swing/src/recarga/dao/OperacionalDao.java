package recarga.dao;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe para carga de dados no banco operacional
 * @author Marcelo
 */
public class OperacionalDao {
    
    private static String LOGIN="aps_lab_banco";
    private static String SENHA="senha_aps_banco";
    private static String BANCO="APS";
    
    private Connection conexao= null;
    private Statement sts= null;
            
    public void conectar(){
    
        try {
            Class.forName("org.postgresql.Driver");
            String jdbc = "jdbc:postgresql://localhost:5432/"+BANCO;
            conexao = DriverManager.getConnection(jdbc,LOGIN, SENHA);
            sts = conexao.createStatement();
            
            
        } catch (ClassNotFoundException ex) {            
        } catch (SQLException ex) {}
        
        try {
            System.out.println("Conectado!:"+ conexao.getSchema());
        } catch (SQLException ex) {
            Logger.getLogger(OperacionalDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void executar(String QUERY){
        
        
        if(sts != null){
            
            try {
                sts.execute(QUERY);
                
                
            } catch (SQLException ex) {System.out.println(ex.getMessage());}
        }
    }
    
    public ResultSet executarExtract(String QUERY) throws SQLException{
        
        ResultSet rs = sts.executeQuery(QUERY);
        return rs;
    }
    
    public  void desconectar(){
        
        try{
            sts.close();
            conexao.close();
        }catch(Exception e){}
        
    }
    
    public ResultSet obterDadosCidade(){
        
        
        ResultSet rs = null;
        try {
            rs = sts.executeQuery(Query.TRANSFORM);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
        return rs;
    }
}
