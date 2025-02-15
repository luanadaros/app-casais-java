package Evento;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import Organizacao.Festa;

public class Casamento {
    private BigInteger id;
    private LocalDate data;
    private LocalTime hora;
    private String local;
    private Festa festa;

    public Casamento(String id,String data, String hora, String local) {
        this.id = new BigInteger(id);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.data = LocalDate.parse(data, formatter);

        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");
        this.hora = LocalTime.parse(hora, formatterHora);
        
        this.local = local;
    }
}
