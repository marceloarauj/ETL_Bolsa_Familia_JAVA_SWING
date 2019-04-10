/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recarga.extract;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import recarga.dao.OperacionalDao;
import recarga.dao.Query;

/**
 *
 * @author Marcelo
 */
public class Extract {
    
    List lista = new ArrayList();
    
    private Extract(){}
    
    public static void extrair(OperacionalDao extrair) throws SQLException{
        
        ResultSet rs = extrair.executarExtract(Query.EXTRACT);
        
        while(rs.next()){
            String nome = rs.getString("uf_nome");
            System.out.println(nome);
        }
        
    }
}
