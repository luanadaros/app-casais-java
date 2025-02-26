package Evento;
import java.math.BigInteger;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

import Atividades.Tarefa;

public class NovoLar {
    private BigInteger id;
    private String rua;
    private int numero;
    private String complemento;
    private ArrayList<Tarefa> tarefas;

    public NovoLar(String id, String rua, String numero, String complemento) throws ParseException{
        this.id = new BigInteger(id);
        this.rua = rua;
        this.numero = Integer.parseInt(numero);
        this.complemento = complemento;
        this.tarefas = new ArrayList<Tarefa>();
    }

    public void adicionarTarefa(Tarefa tarefa) {
        this.tarefas.add(tarefa);
    }

    public boolean temTarefas(){
        if(this.tarefas.isEmpty()){
            return false;
        }

        return true;
    }

    public Double getValorTotal(){
        Double valorTotal = 0.0;

        for(Tarefa t: tarefas){
            valorTotal += t.getValorTotal();
        }

        return valorTotal;
    }

    public boolean tarefasEstaoPagas(){
        for(Tarefa t: tarefas){
            if(!t.tarefaEstaPaga()){
                return false;
            }
        }

        return true;
    }

    public Double getGastosMensais(LocalDate data){
        Double gastosMensais = 0.0;

        if(this.temTarefas()){
            for(Tarefa t: tarefas){
                gastosMensais += t.getGastosMensais(data);
            }
        }

        return gastosMensais;
    }


    public LocalDate getMenorDataInicio(){
        LocalDate menorData = LocalDate.MAX;

        for(Tarefa t: tarefas){
            if(t.getDataInicio().isBefore(menorData)){
                menorData = t.getDataInicio();
            }
        }

        return menorData;
    }

    
}
