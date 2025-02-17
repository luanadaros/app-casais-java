package Evento;
import java.math.BigInteger;
import java.util.ArrayList;

import Organizacao.Tarefa;

public class NovoLar {
    private BigInteger id;
    private String rua;
    private int numero;
    private String complemento;
    private ArrayList<Tarefa> tarefas;

    public NovoLar(String id, String rua, String numero, String complemento) {
        this.id = new BigInteger(id);
        this.rua = rua;
        this.numero = Integer.parseInt(numero);
        this.complemento = complemento;
        this.tarefas = new ArrayList<Tarefa>();
    }

    public void adicionarTarefa(Tarefa tarefa) {
        this.tarefas.add(tarefa);
    }
}
