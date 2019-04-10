/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajuda;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import recarga.dao.OperacionalDao;
import recarga.dao.Query;
import recarga.extract.Extract;
import recarga.model.Model;


// Classe para reduzir o tempo de obter todos os dados 
public class PegarTodosOsDados {
    public static void main(String[] args) {
        
        try {
            OperacionalDao extract = new OperacionalDao();
            extract.conectar();
            Extract.extrair(extract);
            extract.desconectar();
        } catch (SQLException ex) {

        }
    }
    
}
