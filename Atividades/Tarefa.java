package Atividades;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Utilitarios.ConversorNumerico;

import java.math.BigInteger;
import java.text.ParseException;

public class Tarefa {
    private BigInteger id;
    private BigInteger idPrestador;
    private LocalDate dataInicio;
    private int prazoEntrega;
    private double valorPrestador;
    private int numParcelas;
    private int qtdParcelasPagas = 0;
    private ArrayList<Compra> compras;

    public Tarefa(String id, String idPrestador, String dataInicio, String prazoEntrega, String valorPrestador, String numParcelas) throws ParseException{
        this.id = new BigInteger(id);
        this.idPrestador = new BigInteger(idPrestador);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dataInicio = LocalDate.parse(dataInicio, formatter);

        this.prazoEntrega = Integer.parseInt(prazoEntrega);

         try {
            this.valorPrestador = ConversorNumerico.converterParaDouble(valorPrestador);
        } catch (ParseException e){
            System.out.println(e);
        }
        this.numParcelas = Integer.parseInt(numParcelas);

        this.compras = new ArrayList<Compra>();
    }

    //sets
    public void adicionarCompra(Compra compra){
        this.compras.add(compra);
    }

    public void pagarParcela(){
        if(qtdParcelasPagas < numParcelas){
            qtdParcelasPagas++;
        }
    }
    
    //verificacoes
    public boolean temCompras(){
        if(this.compras.isEmpty()){
            return false;
        }

        return true;
    }

    public boolean comprasEstaoPagas(){
        for(Compra c: this.compras){
            if(!c.compraEstaPaga()){
                return false;
            }
        }
        
        return true;
    }
    
    public boolean tarefaEstaPaga(){
        if(qtdParcelasPagas == numParcelas){
            return true;
        }
        return false;
    }

    //gets
    public BigInteger getIdPrestador() {
        return idPrestador;
    }

    public double getValorTotal() {
        double valorTotal = 0;

        valorTotal += valorPrestador;
        for(Compra c: this.compras){
            valorTotal += c.getValorTotal();
        }

        return valorTotal;
    }

    public double getValorPrestador(){
        return this.valorPrestador;
    }

    public double getValorCompras(){
        double valorTotal = 0;
        for(Compra c: this.compras){
            valorTotal += c.getValorTotal();
        }

        return valorTotal;
    }

    public Double getGastosMensais(LocalDate data){
        Double gastosMensais = 0.0;

        if(this.temCompras()){
            for(Compra c: this.compras){
                if(!c.compraEstaPaga()){
                    if(dataInicio.getYear() == data.getYear() && (dataInicio.getMonthValue() <= data.getMonthValue()) && (dataInicio.getMonthValue() + c.getNumParcelas()) > data.getMonthValue()){
                        gastosMensais += c.getValorParcela();
                        c.pagarParcela();
                    } else if(dataInicio.getYear() < data.getYear() && (dataInicio.getMonthValue() + c.getNumParcelas() - 12) > data.getMonthValue()){
                        gastosMensais += c.getValorParcela();
                        c.pagarParcela();
                    }
                }
            }
        }
        
        if(!tarefaEstaPaga()){
            if(dataInicio.getYear() == data.getYear() && (dataInicio.getMonthValue() <= data.getMonthValue()) && (dataInicio.getMonthValue() + numParcelas) > data.getMonthValue()){
                gastosMensais += valorPrestador / numParcelas;
                this.pagarParcela();
            } else if(dataInicio.getYear() < data.getYear() && (dataInicio.getMonthValue() + numParcelas - 12) > data.getMonthValue()){
                gastosMensais += valorPrestador / numParcelas;
                this.pagarParcela();
            }
        }

        return gastosMensais;
    }

    public LocalDate getDataInicio(){
        return this.dataInicio;
    }
}
