package Organizacao;
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

    public Tarefa(String id, String idPrestador, String dataInicio, String prazoEntrega, String valorPrestador, String numParcelas){
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
}
