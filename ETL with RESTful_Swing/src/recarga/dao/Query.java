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
                                 + "nome_ibge FROM DADOS";
    
    
    //essa query serve para preencher a dimensão cidade, seleciona apenas
    // todas as cidades do primeiro mês de 2016
    public static String TRANSFORM = "SELECT uf_sigla,"
                                 + "uf_nome,"
                                 + "nome_ibge FROM DADOS "
                                 + "WHERE EXTRACT(YEAR FROM data_referencia)=2016 "
                                 + "AND EXTRACT(MONTH FROM data_referencia)=1"; 
    
    public static String LOAD_DM_CIDADE ="INSERT INTO dm_cidade"
                                       + "(id_cidade,"
                                       + "cidade,"
                                       + "estado,"
                                       + "regiao)"
                                       + "VALUES";
    
    public static String LOAD_DM_TEMPO = "INSERT INTO dm_tempo"
                                       + "(id_tempo,"
                                       + "mes,"
                                       + "ano,"
                                       + "bimestre,"
                                       + "trimestre,"
                                       + "semestre)"
                                       + "VALUES";
    
    public static String LOAD_FC_TOTAL = "INSERT INTO ft_valor"
                                       + "(id_cidade,"
                                       + "id_tempo,"
                                       + "beneficiados,"
                                       + "valor)"
                                       + "VALUES";
}
