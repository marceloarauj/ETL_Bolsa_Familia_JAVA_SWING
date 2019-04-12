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
public class Ft_Valores {
    
    private int idTempo;
    private int idCidade;
    private int beneficiados;
    private double valor;

    public Ft_Valores(int idTempo, int idCidade, int beneficiados, double valor) {
        
        this.idTempo = idTempo;
        this.idCidade = idCidade;
        this.beneficiados = beneficiados;
        this.valor = valor;
    }

    
    
    public int getIdTempo() {
        return idTempo;
    }

    public int getIdCidade() {
        return idCidade;
    }

    public int getBeneficiados() {
        return beneficiados;
    }

    public double getValor() {
        return valor;
    }
    
    
}
