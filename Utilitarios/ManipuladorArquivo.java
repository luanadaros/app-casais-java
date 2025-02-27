package Utilitarios;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class ManipuladorArquivo {
    public static void lerArquivo(String caminhoArquivo, List<String> dadosAgrupados) throws Exception{
        BufferedReader leitor = new BufferedReader(new InputStreamReader(new FileInputStream(caminhoArquivo),"UTF-8"));
        String linha;

        while((linha = leitor.readLine()) != null){
            dadosAgrupados.add(linha);
        }
        leitor.close();
    }

    public static void escreverArquivo(FileOutputStream fos, List<String>dados) throws Exception {
        BufferedWriter escritor = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"));

        if(dados == null){
            escritor.close();
            return;
        }
        
        for(String linha : dados){
            escritor.write(linha);
        }

        escritor.close();
    }
}
