/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recarga.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Classe para carga em Data Warehouse
 * @author Marcelo
 */
public class WarehouseDao {
    
    private static String LOGIN="aps_lab_banco";
    private static String SENHA="senha_aps_banco";
    private static String BANCO="APS_DATAWAREHOUSE";
    
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
        
    public  void desconectar(){
        
        try{
            sts.close();
            conexao.close();
        }catch(Exception e){}
        
    }
   
}
