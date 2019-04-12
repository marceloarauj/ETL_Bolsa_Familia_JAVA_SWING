/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recarga.load;

import java.sql.SQLException;
import java.util.ArrayList;
import recarga.dao.Query;
import recarga.dao.WarehouseDao;
import recarga.model.DadosTransformados;
import recarga.model.Dm_Cidade;
import recarga.model.Dm_Tempo;
import recarga.model.Ft_Valores;
import recarga.transform.Transform;

/**
 *
 * @author Marcelo
 */
public class Load {
    
    private WarehouseDao load;
    
    public void Carregar() throws SQLException{
        
        DadosTransformados dados = Transform.obterDados();
        
        ArrayList<Dm_Cidade> cidades = dados.getDadosCidade();
        ArrayList<Dm_Tempo> tempos = dados.getDadosTempo();
        ArrayList<Ft_Valores> valores = dados.getDadosFacts();
        
        load = new WarehouseDao();
        
        load.conectar();
        
        for(Dm_Cidade cif: cidades){
        
            load.executar(Query.LOAD_DM_CIDADE +"("+ cif.getIdCidade()+","
                                                   +"'"+cif.getCidade()+"'"+","
                                                   +"'"+cif.getEstado()+"'"+","
                                                    +"'"+cif.getRegiao()+"'"+")");
        }
        try{
        for(Dm_Tempo temps: tempos){
            load.executar(Query.LOAD_DM_TEMPO+"("+temps.getIdTempo()+","
                                                 +temps.getMes()+","
                                                 +temps.getAno()+","
                                                 +temps.getBimestre()+","
                                                 +temps.getTrimestre()+","
                                                 +temps.getSemestre()+")");
        }
        }catch(Exception e){System.out.println(e.getMessage());}
        for(Ft_Valores values: valores){
            load.executar(Query.LOAD_FC_TOTAL+"("+values.getIdCidade()+","
                                                 +values.getIdTempo()+","
                                                 +values.getBeneficiados()+","
                                                 +values.getValor()+")");
        }
        
        
        
        load.desconectar();
    }
}
