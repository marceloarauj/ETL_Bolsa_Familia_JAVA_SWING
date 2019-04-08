/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recarga.controller;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import recarga.model.Model;



public class Controller {

    private static URL url;
    private static HttpURLConnection conexao;
    private static Model[] model;
    
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
          catch (IOException ex) {System.out.println(ex.getLocalizedMessage());}
          catch (Exception e){System.out.println(e.getLocalizedMessage());}
        
        return model;
    }
    
           
}
