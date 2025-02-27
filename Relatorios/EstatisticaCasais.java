package Relatorios;

import java.util.List;
import java.util.ArrayList;
import Utilitarios.Cadastro;
import Utilitarios.ManipuladorArquivo;
import java.util.Map;
import java.util.HashMap;
import java.io.FileOutputStream;
import java.math.BigInteger;
import Casal.Casal;
import Evento.*;
import Utilitarios.ConversorNumerico;

public class EstatisticaCasais {
    private static int quantidadeCasamentosConvidados(Cadastro cadastro, Casal casal){
        int numCasamentosConvidados = 0;
        Map<BigInteger, Casamento> casamentos = cadastro.getMapCasamentos();
        String[] nomes = casal.getNomes();

        for(Casamento c: casamentos.values()){
            if(c.temConvidado(nomes[0]) && c.temConvidado(nomes[1])){
                numCasamentosConvidados++;
            }
        }
        return numCasamentosConvidados;
    }

    public static void geraRelatorioCasais(String caminhoArquivo, Cadastro cadastro) throws Exception{
        List<String[]> estatisticaCasais = new ArrayList<>();
        Map<BigInteger, Casal> casais = cadastro.getMapCasais();
        Map<Casal, Integer> casaisVisitados = new HashMap<>();

        for(Casal casal: casais.values()){
            if(casaisVisitados.containsKey(casal)){
                continue;
            }

            String[] dados = new String[4];
            String[] nomes = casal.getNomes();

            if(nomes[1].compareTo(nomes[0]) == -1){
                dados[0] = nomes[1];
                dados[1] = nomes[0];
            } else {
                dados[0] = nomes[0];
                dados[1] = nomes[1];
            }

            double totalGasto = 0;
            int numCasamentosConvidados = 0;

            if(casal.temCasamento()){
                Casamento c = casal.getCasamento();
                if(c.temFesta()){
                    totalGasto += c.getValorFesta();
                }
            }

            if(casal.temNovoLar()){
                NovoLar l = casal.getNovoLar();
                if(l.temTarefas()){
                    totalGasto += l.getValorTotal();
                }
            }

            dados[2] = Double.toString(totalGasto);
            numCasamentosConvidados = quantidadeCasamentosConvidados(cadastro, casal);
            dados[3] = Integer.toString(numCasamentosConvidados);

            estatisticaCasais.add(dados);
            casaisVisitados.put(casal, 1);
        }

        //ordena estatisticaCasais
        estatisticaCasais.sort((a, b) -> {   
            double gastoA = Double.parseDouble(a[2]);
            double gastoB = Double.parseDouble(b[2]);
            
            if(gastoA > gastoB){
                return -1;
            } else if(gastoA < gastoB){
                return 1;
            } else {
                return a[0].compareTo(b[0]);
            }
        });

        List<String> listaDadosFinais = new ArrayList<>();
        for(String[] v: estatisticaCasais){
            v[2] = ConversorNumerico.converterParaMoedaBR(Double.parseDouble(v[2]));
            String resultado = String.join(";", v) + "\n";
            listaDadosFinais.add(resultado);
        }

        FileOutputStream fos = new FileOutputStream(caminhoArquivo, false);
        ManipuladorArquivo.escreverArquivo(fos, listaDadosFinais);
    }
}
