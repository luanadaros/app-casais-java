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
    private Double preco;
    private int numParcelas;
    private int qtdParcelasPagas = 0;
    private LocalDate data;
    private LocalTime hora;
    private int numConvidados;
    private ArrayList<String> listaConvidados;
    private Double valorParcela; 

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

    public Double getValorParcela(){
        return this.valorParcela;
    }

    public Double getValorTotal(){
        return this.preco;
    }

    public ArrayList<String> getConvidados(){
        return this.listaConvidados;
    }

    public void pagarParcela(){
        if(this.qtdParcelasPagas < this.numParcelas){
            this.qtdParcelasPagas++;
        }
    }

    public boolean festaEstaPaga(){
        if(this.qtdParcelasPagas >= this.numParcelas){
            return true;
        }
        return false;
    }

    public Double getGastosMensais(LocalDate data){
        if(this.data.getYear() == data.getYear() && (this.data.getMonthValue() <= data.getMonthValue()) && (this.data.getMonthValue() + numParcelas > data.getMonthValue())){
            this.pagarParcela();
            return this.getValorParcela();
        } else if(this.data.getYear() != data.getYear() && ((this.data.getMonthValue() + numParcelas - 12) > data.getMonthValue())){
            this.pagarParcela();
            return this.getValorParcela();
        }
        return 0.0;
    }

    public LocalDate getData(){
        return this.data;
    }
}
