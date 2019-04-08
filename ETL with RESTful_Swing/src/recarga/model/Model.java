
package recarga.model;

import java.util.Date;

public class Model {
    
    private double valor;
    private String dataReferencia;
    private int quantidadeBeneficiados;
    private Municipio municipio;

    public String getDataReferencia() {
        return dataReferencia;
    }

    public void setDataReferencia(String dataReferencia) {
        this.dataReferencia = dataReferencia;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQuantidadeBeneficiados() {
        return quantidadeBeneficiados;
    }

    public void setQuantidadeBeneficiados(int quantidadeBeneficiados) {
        this.quantidadeBeneficiados = quantidadeBeneficiados;
    }
    
  
}
