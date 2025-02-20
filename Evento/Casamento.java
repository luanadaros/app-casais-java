package Evento;
import java.math.BigInteger;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import Atividades.Festa;

public class Casamento {
    private BigInteger id;
    private LocalDate data;
    private LocalTime hora;
    private String local;
    private Festa festa = null;

    public Casamento(String id,String data, String hora, String local) throws ParseException{
        this.id = new BigInteger(id);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.data = LocalDate.parse(data, formatter);

        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");
        this.hora = LocalTime.parse(hora, formatterHora);
        
        this.local = local;
    }

    public void setFesta(Festa festa) {
        this.festa = festa;
    }

    public boolean temFesta(){
        if(this.festa == null){
            return false;
        } 

        return true;
    }

    public double getValorFesta(){
        double valor = this.festa.getValorTotal();

        return valor;
    }

    public boolean temConvidado(String nome){
        if(this.festa == null){
            return false;
        }
        
        for(String convidado: this.festa.getConvidados()){
            if(convidado.equals(nome)){
                return true;
            }
        }
        return false;
    }
}
