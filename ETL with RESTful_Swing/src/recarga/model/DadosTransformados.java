/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recarga.model;

import java.util.ArrayList;


public class DadosTransformados {
    
    private ArrayList<Dm_Cidade> dadosCidade = new ArrayList<>();
    private ArrayList<Dm_Tempo> dadosTempo = new ArrayList<>();
    private ArrayList<Ft_Valores> dadosFacts = new ArrayList<>();

    public ArrayList<Dm_Cidade> getDadosCidade() {
        return dadosCidade;
    }

    public void setDadosCidade(ArrayList<Dm_Cidade> dadosCidade) {
        this.dadosCidade = dadosCidade;
    }

    public ArrayList<Dm_Tempo> getDadosTempo() {
        return dadosTempo;
    }

    public void setDadosTempo(ArrayList<Dm_Tempo> dadosTempo) {
        this.dadosTempo = dadosTempo;
    }

    public ArrayList<Ft_Valores> getDadosFacts() {
        return dadosFacts;
    }

    public void setDadosFacts(ArrayList<Ft_Valores> dadosFacts) {
        this.dadosFacts = dadosFacts;
    }
    
    
}
