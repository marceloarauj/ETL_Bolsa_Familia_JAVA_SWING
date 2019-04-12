/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recarga.model;

/**
 *
 * @author Marcelo
 */
public class Dm_Tempo {
    
    private int idTempo;
    private int mes;
    private int ano;
    private int bimestre;
    private int trimestre;
    private int semestre;

    public Dm_Tempo(int idTempo, int mes, int ano, int bimestre, int trimestre, int semestre) {
        this.idTempo = idTempo;
        this.mes = mes;
        this.ano = ano;
        this.bimestre = bimestre;
        this.trimestre = trimestre;
        this.semestre = semestre;
    }

    
    
    public int getIdTempo() {
        return idTempo;
    }

    public int getMes() {
        return mes;
    }

    public int getBimestre() {
        return bimestre;
    }

    public int getTrimestre() {
        return trimestre;
    }

    public int getSemestre() {
        return semestre;
    }

    public int getAno() {
        return ano;
    }
    
}
