/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recarga.dao;

//interface com querys utilizadas 
public interface Query {
    
    public static String INSERT = "INSERT INTO DADOS"
                                    +"(uf_sigla,"
                                    + "uf_nome,"
                                    + "codigo_ibge,"
                                    + "nome_ibge,"
                                    + "pais,"
                                    + "valor,"
                                    + "beneficiados,"
                                    + "data_referencia)"
                                 +"VALUES";
    
    public static String EXTRACT = "SELECT uf_sigla,"
                                 + "uf_nome,"
                                 + "data_referencia,"
                                 + "beneficiados,"
                                 + "valor,"
                                 + "data_referencia FROM DADOS";
     
}
