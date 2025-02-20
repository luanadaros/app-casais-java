package Atividades;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Utilitarios.ConversorNumerico;

import java.math.BigInteger;
import java.text.ParseException;

public class Festa {
    private BigInteger id;
    private String local;
    private double preco;
    private int numParcelas;
    private LocalDate data;
    private LocalTime hora;
    private int numConvidados;
    private ArrayList<String> listaConvidados;
    private double valorParcela; 

    public Festa(String id, String local, String data, String hora, String valorPago, String numParcelas, String numConvidados) throws ParseException{
        this.id = new BigInteger(id);
        this.local = local;
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.data = LocalDate.parse(data, formatter);

        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");
        this.hora = LocalTime.parse(hora, formatterHora);

        this.preco = ConversorNumerico.converterParaDouble(valorPago);

        this.numParcelas = Integer.parseInt(numParcelas);
        this.valorParcela = this.preco/this.numParcelas;

        this.numConvidados = Integer.parseInt(numConvidados);
        this.listaConvidados = new ArrayList<String>();
    }

    public void adicionarConvidado(String convidado){
        this.listaConvidados.add(convidado);
    }

    public double getValorParcela(){
        return this.valorParcela;
    }

    public double getValorTotal(){
        return this.preco;
    }

    public ArrayList<String> getConvidados(){
        return this.listaConvidados;
    }
}
