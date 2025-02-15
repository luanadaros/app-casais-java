package Organizacao;
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

    public Festa(String id, String local, String data, String hora, String valorPago, String numParcelas, String numConvidados, String[] convidados){
        this.id = new BigInteger(id);
        this.local = local;
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.data = LocalDate.parse(data, formatter);

        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");
        this.hora = LocalTime.parse(hora, formatterHora);

         try {
            this.preco = ConversorNumerico.converterParaDouble(valorPago);
        } catch (ParseException e){
            System.out.println(e);
        }

        this.numParcelas = Integer.parseInt(numParcelas);
        this.numConvidados = Integer.parseInt(numConvidados);

        for(String convidado: convidados){
            listaConvidados.add(convidado);
        }
    }
}
