package Utilitarios;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class ConversorNumerico {
    private static final NumberFormat format = NumberFormat.getInstance(Locale.of("pt", "BR"));
    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.of("pt", "BR"));

    public static double converterParaDouble(String valor) throws ParseException {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("Erro de formatação");
        }
        
        return format.parse(valor).doubleValue();
    }

    public static String converterParaMoedaBR(double valor){
        String valorFormatado = currencyFormat.format(valor);
        valorFormatado = valorFormatado.replaceAll("[^\\d,]", ""); 
        
        return "R$ " + valorFormatado;
    }
}
