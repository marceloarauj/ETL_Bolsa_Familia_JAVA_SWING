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
public class Dm_Cidade {
    
    private int idCidade;
    private String cidade;
    private String regiao;
    private String estado;
    
    public Dm_Cidade(int idCidade,String cidade,String regiao,String estado){
        
        this.idCidade = idCidade;
        this.cidade = cidade;
        this.estado = estado;
        this.regiao = regiao;
    }

    public int getIdCidade() {
        return idCidade;
    }

    public String getCidade() {
        return cidade;
    }

    public String getRegiao() {
        return regiao;
    }

    public String getEstado() {
        return estado;
    }
    
    
}
