package Relatorios;

import java.util.List;
import java.util.ArrayList;
import Casal.Casal;

public class Planejamento {
    private List<List<String>> planejamentosCasais;

    public Planejamento(){
        planejamentosCasais = new ArrayList<>();
    } 

    private void adicionaPlanejamento(List<String> planejamento){
        this.planejamentosCasais.add(planejamento);
    }

    public void calculaPlanejamento(String cpf1, String cpf2){

    }
}
