/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recarga.controller;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import recarga.dao.OperacionalDao;
import recarga.dao.Query;
import recarga.dao.WarehouseDao;
import recarga.extract.Extract;
import recarga.load.Load;
import recarga.model.Model;
import recarga.view.View;



public class Controller {

    private static URL url;
    private static HttpURLConnection conexao;
    private static Model[] model;
    private static int barraDeProgresso =0;
    
    private static OperacionalDao bancoOperacional;
    private static WarehouseDao   dataWarehouse;
    private static Thread thread;
    
    // Método de teste , retorna somente 1 json
    public static Model[] obterDados
            (String estado, String municipio, String ano, String mes){
        
        
        StringBuilder str = new StringBuilder();
        
        str.append("http://www.transparencia.gov.br/"+
                   "api-de-dados/bolsa-familia-por-municipio/"+
                    "?mesAno=");
        
        str.append(ano);
        str.append(mes);
        str.append("&codigoIbge=");
        str.append(estado);
        str.append(municipio);
        str.append("&pagina=1");
        
        
        try {
            url = new URL(str.toString());
            conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");
            conexao.setRequestProperty("Accept", "application/json");
            
            if(conexao.getResponseCode() != 200){
                throw new RuntimeException("Falha: "+ conexao.getResponseCode());
            }
            
            InputStreamReader entrada = 
                    new InputStreamReader(conexao.getInputStream());
            
            BufferedReader br = new BufferedReader(entrada);

            Gson json = new Gson();
 
            model = json.fromJson(br, Model[].class);
            
            
            conexao.disconnect();


        } catch (MalformedURLException ex) {System.out.println(ex.getLocalizedMessage());} 
          catch (Exception e){System.out.println(e.getLocalizedMessage());}
        
        return model;
    }
    
    // carregar todos os dados
    public static List<Model[]> todosOsDados(){
    
        bancoOperacional = new OperacionalDao();
        bancoOperacional.conectar();
        
        List<Model[]> lista = new ArrayList<Model[]>();
        //Thread separada
         Thread th = new Thread(new Runnable(){
            @Override
            public void run() {
                
                int ano = 2016;
                int mes = 2;
                String estado = "";
                String municipio = "";
                boolean chave = false;
                //listar todos os itens de todos os anos 
                while(ano < 2019){
                    
                    if(!chave){
                    mes = 2;
                    chave = true;
                    }else{
                    mes = 1;
                    }
                    while(mes < 13){
                        
                        //abrir
                        FileInputStream arquivo = null;
        
                        try {
                            arquivo = new FileInputStream
                                ("src/resources/Municípios-IBGE.csv");
                        } catch (FileNotFoundException ex) {}
        
                        InputStreamReader ip = new InputStreamReader(arquivo);
                        BufferedReader brl = new BufferedReader(ip);
        
                        String linha = null;
                        int i =0;
                        
                        try {
                            while((linha = brl.readLine())!= null){
                                try{
                                    String[] dados = linha.split(","); 
                                
                                    estado = dados[1].replace("\"", "");
                                    municipio = dados[2].replace("\"", "");
                                
                                }catch(Exception e){
                                    mes = 0;
                                    ano = 0;
                                    estado = "00";
                                    municipio = "00000";
                                }
                                StringBuilder str = new StringBuilder();
        
                                str.append("http://www.transparencia.gov.br/"+
                                "api-de-dados/bolsa-familia-por-municipio/"+
                                 "?mesAno=");
        
                                str.append(ano);
                                
                                if(mes < 10){
                                    str.append("0"+mes);
                                }else{
                                    str.append(mes);
                                }
                                
                                str.append("&codigoIbge=");
                                str.append(estado);
                                str.append(municipio);
                                str.append("&pagina=1");
                                
                               
                                barraDeProgresso++;
                                View.jProgressBar1.setValue(barraDeProgresso);
                                View.jButton2.setText(""+barraDeProgresso);
                                try{
                                    url = new URL(str.toString());
                                    
                                    conexao = (HttpURLConnection) 
                                            url.openConnection();
                                    
                                    conexao.setRequestMethod("GET");
                                    
                                    conexao.setRequestProperty
                                        ("Accept", "application/json");
                                    
                                    if(conexao.getResponseCode() == 200){
                                        
                                        InputStreamReader entrada = 
                                                new InputStreamReader
                                                    (conexao.getInputStream());
                                        
                                        BufferedReader br = 
                                                new BufferedReader(entrada);
                                        
                                        Gson json = new Gson();
                                        
                                        Model[] modelo = 
                                                json.fromJson(br,Model[].class);
                                        
                                        
                                        
                                        inserir(modelo);
                                        
                                        lista.add(modelo);
                                    }
                                    
                                    conexao.disconnect();
                                    
                                }catch(Exception e){}
                            }
                        } catch (IOException ex) {
            
                        }        
                       //Fechar
                        mes++;
                    }
                    ano++;
                }
                
            }
        }); 
        
        th.start();
        
        thread = th;
        
        return lista;
    }
    
    private static void inserir(Model[] modelo){
        
        Model mod = modelo[0];
        bancoOperacional.executar
            (Query.INSERT+"("+"'"+mod.getMunicipio().getUf().getSigla()+"'"+","
                             +"'"+mod.getMunicipio().getUf().getNome()+"'"+","
                             +"'"+mod.getMunicipio().getCodigoIBGE()+"'"+","
                             +"'"+mod.getMunicipio().getNomeIBGE()+"'"+","
                             +"'"+mod.getMunicipio().getPais()+"'"+","
                             +mod.getValor()+","
                             +mod.getQuantidadeBeneficiados()+","
                             +"TO_DATE("+"'"+mod.getDataReferencia()+"'"+",'dd/mm/yyyy'"+")"+")");
    }
    
    
    public static Thread retornaThread(){
        return thread;
    }
    
    public static void fecharConexao(){
        bancoOperacional.desconectar();
    }
    
    public static void efetuarETL() throws SQLException{
        Load load = new Load();
        load.Carregar();
    }
    
}
