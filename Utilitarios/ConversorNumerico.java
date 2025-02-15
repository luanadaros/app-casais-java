package Utilitarios;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class ConversorNumerico {
    private static final NumberFormat format = NumberFormat.getInstance(new Locale("pt", "BR"));

    public static double converterParaDouble(String valor) throws ParseException {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("Erro de formatação");
        }
        
        return format.parse(valor).doubleValue();
    }
}
