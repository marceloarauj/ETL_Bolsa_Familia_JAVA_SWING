/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recarga.transform;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import recarga.dao.OperacionalDao;
import recarga.dao.Query;
import recarga.extract.Extract;
import recarga.model.DadosTransformados;
import recarga.model.Dm_Cidade;
import recarga.model.Dm_Tempo;
import recarga.model.Ft_Valores;

/**
 *
 * @author Marcelo
 */
public class Transform {
    
    private static ResultSet dados;
    private static HashMap<String, String> mapaRegioes = new HashMap<>();
    
    public static DadosTransformados obterDados() throws SQLException{
        
        OperacionalDao extrair = new OperacionalDao();
        
        extrair.conectar();
        
        Extract.extrair(extrair);
        dados = Extract.resultadoExtracao();
        
        extrair.desconectar();
        
        return transformar();
    }
    
    private static DadosTransformados transformar() throws SQLException{
        
        // carregar mapa
        carregarMapa();
        
        // armazenar os dados transformados
        DadosTransformados dadosTransformados = new DadosTransformados();
        
        // Dados transformados armazenos
        ArrayList<Dm_Cidade> transformCidade = new ArrayList<>();
        ArrayList<Dm_Tempo> transformTempo = new ArrayList<>();
        ArrayList<Ft_Valores> transformFatos = new ArrayList<>();
        
        OperacionalDao op = new OperacionalDao();
        op.conectar();
        
        ResultSet rs = Extract.dadosCidade(op);
       
        
        //transformar cidade
        int idCidade = 1;
        while(rs.next()){
            
            String cidade = rs.getString("nome_ibge");
            String estado = rs.getString("uf_nome");
            String regiao = mapaRegioes.get(rs.getString("uf_sigla"));
            
            Dm_Cidade cidadeTransf = 
                    new Dm_Cidade(idCidade,cidade,regiao,estado);
            
            transformCidade.add(cidadeTransf);
            idCidade ++;
        }
        
        // transformar tempo
        int ano = 2016;
        int mes = 1;
        int idTempo =1;
        
        while(ano < 2019){
            
            mes = 1;
            
            int bimestre =0;
            int trimestre =0;
            int semestre = 0;
            
            while(mes < 13){
                
                if(mes< 7){
                    semestre = 1;
                    
                    if(mes < 4){
                        trimestre = 1;
                    }else{
                        trimestre = 2;
                    }
                }else{
                    semestre = 2;
                    
                    if(mes < 10){
                        trimestre = 3;
                    }else{
                        trimestre = 4;
                    }
                }
                //bimestre
                if(mes == 1 | mes == 2){ bimestre = 1;}
                if(mes == 3 | mes == 4){bimestre = 2;}
                if(mes == 5 | mes == 6){bimestre = 3;}
                if(mes == 7 | mes == 8){bimestre = 4;}
                if(mes == 9 | mes == 10){bimestre = 5;}
                if(mes == 11 | mes == 12){bimestre = 6;}
                
                Dm_Tempo tempoTransf = 
                        new Dm_Tempo(idTempo,mes,ano,bimestre,trimestre,semestre);
                
                transformTempo.add(tempoTransf);
                
                mes++;
                idTempo++;
                System.out.println(idTempo);
            }
            
            ano++;
        }
        // preencher tabelaDeFatos
        dados = op.executarExtract(Query.EXTRACT);
        while(dados.next()){
             
            double total = dados.getDouble("valor");
            int beneficiados = dados.getInt("beneficiados");
            int idTempo1 = 0;
            int idCidade1 = 0;
            
            for(Dm_Cidade cids: transformCidade){
                if(dados.getString("nome_ibge").equals(cids.getCidade())){
                    idCidade1 = cids.getIdCidade();
                }
            }
            
            for(Dm_Tempo temps: transformTempo){
                Date data = dados.getDate("data_referencia");
                LocalDate ld = data.toLocalDate();
                
                if(temps.getMes()== ld.getDayOfMonth() & temps.getAno()==ld.getYear()){
                    idTempo1 = temps.getIdTempo();
                }
            }
            
            Ft_Valores valores = 
                    new Ft_Valores(idTempo1,idCidade1,beneficiados,total);
            
            transformFatos.add(valores);
        }
        
        dadosTransformados.setDadosCidade(transformCidade);
        dadosTransformados.setDadosTempo(transformTempo);
        dadosTransformados.setDadosFacts(transformFatos);
        op.desconectar();
        
        return dadosTransformados;
    }
    
    private static void carregarMapa(){
        
        //Norte
        mapaRegioes.put("AM","Norte");
        mapaRegioes.put("RR","Norte");
        mapaRegioes.put("AP","Norte");
        mapaRegioes.put("PA","Norte");
        mapaRegioes.put("TO","Norte");
        mapaRegioes.put("RO","Norte");
        mapaRegioes.put("AC","Norte");
        
        //Nordeste
        mapaRegioes.put("MA", "Nordeste");
        mapaRegioes.put("PI", "Nordeste");
        mapaRegioes.put("CE", "Nordeste");
        mapaRegioes.put("RN", "Nordeste");
        mapaRegioes.put("PE", "Nordeste");
        mapaRegioes.put("PB", "Nordeste");
        mapaRegioes.put("SE", "Nordeste");
        mapaRegioes.put("AL", "Nordeste");
        mapaRegioes.put("BA", "Nordeste");
        
        //Centro-Oeste
        
        mapaRegioes.put("MT", "Centro-Oeste");
        mapaRegioes.put("MS", "Centro-Oeste");
        mapaRegioes.put("GO", "Centro-Oeste");
        
        //Sudeste
        mapaRegioes.put("SP", "Sudeste");
        mapaRegioes.put("RJ", "Sudeste");
        mapaRegioes.put("ES", "Sudeste");
        mapaRegioes.put("MG", "Sudeste");
        
        //Sul
        mapaRegioes.put("PR", "Sul");
        mapaRegioes.put("RS", "Sul");
        mapaRegioes.put("SC", "Sul");
        
    }
   
}
