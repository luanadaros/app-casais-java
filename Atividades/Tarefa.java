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

    public void adicionarCompra(Compra compra){
        this.compras.add(compra);
    }

    public boolean temCompras(){
        if(this.compras.isEmpty()){
            return false;
        }

        return true;
    }

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
                if(dataInicio.getYear() == data.getYear() && (dataInicio.getMonthValue() <= data.getMonthValue()) && (dataInicio.getMonthValue() + numParcelas) >= data.getMonthValue()){
                    gastosMensais += c.getValorParcela();
                }
            }
        }
        
        if(dataInicio.getYear() == data.getYear() && (dataInicio.getMonthValue() <= data.getMonthValue()) && (dataInicio.getMonthValue() + numParcelas) >= data.getMonthValue()){
            gastosMensais += valorPrestador / numParcelas;
        }

        return gastosMensais;
    }

    public LocalDate getDataInicio(){
        return this.dataInicio;
    }
}
