package Relatorios;

import java.util.List;
import java.util.ArrayList;
import Casal.Casal;
import Utilitarios.ConversorNumerico;
import Utilitarios.ManipuladorArquivo;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Planejamento {
    public static List<String[]> calculaPlanejamento(Casal casal, String cpf1, String cpf2){
        List<String[]> saldos = new ArrayList<String[]>();
        boolean casalEncontrado = casal != null;

        if(!casalEncontrado){
            saldos = null;
            return saldos;
        } else {
            if(!casal.temFesta() && !casal.temTarefas()){
                saldos = null;
                return saldos;
            } else {
                LocalDate dataInicio = casal.getDataInicioPlanejamento();
                LocalDate dataAtual = dataInicio;
                DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("MM/yyyy");
                casal.atualizaPoupancaCasalRendimento();
                
                while(true){
                    Double gastos = casal.calculaGastosCasalNoMes(dataAtual);
                    if(gastos == 0.0){
                        break;
                    }

                    Double saldo = casal.calculaSaldoCasalNoMes(dataAtual, gastos);
                    String[] saldoMes = new String[2];
                    saldoMes[0] = dataAtual.format(formatoData);
                    saldoMes[1] = ConversorNumerico.converterParaMoedaBR(saldo);
                    saldos.add(saldoMes);

                    dataAtual = dataAtual.plusMonths(1);
                }
                
            }
        }

        return saldos;
    }

    public static void imprimePlanejamento(List<String[]> saldos){
        if(saldos.isEmpty()){
            return;
        }

        System.out.println("Planejamento de gastos:");
        for(String[] saldo: saldos){
            System.out.print(saldo[0] + " " + saldo[1] + "; ");
        }
        System.out.println();
    }

    public static void atualizaRelatorioPlanejamento(String caminhoArquivo, List<String[]> saldos, Casal casal, String cpf1, String cpf2) throws Exception {
        List<String> dadosCabecalhos = new ArrayList<String>();
        List<String> dadosSaldos = new ArrayList<String>();
        List<String> dadosFinais = new ArrayList<String>();

        if(casal == null){
            dadosSaldos.add("Casal com CPFs " + cpf1 + " e " + cpf2 + " não está cadastrado.\n");
            FileOutputStream fos = new FileOutputStream(caminhoArquivo, true);
            ManipuladorArquivo.escreverArquivo(fos,dadosSaldos);
            return;
        }

        if(saldos == null){
            dadosSaldos.add("Casal com CPFs " + cpf1 + " e " + cpf2 + " não possui gastos cadastrados.\n");
            FileOutputStream fos = new FileOutputStream(caminhoArquivo, true);
            ManipuladorArquivo.escreverArquivo(fos,dadosSaldos);
            return;
        }

        dadosCabecalhos.add("Nome 1");
        dadosCabecalhos.add("Nome 2");
        String[] nomes = casal.getNomes();
        dadosSaldos.add(nomes[0]);
        dadosSaldos.add(nomes[1]);

        for(String[] saldo: saldos){
            dadosCabecalhos.add(saldo[0]);
            dadosSaldos.add(saldo[1]);
        }

        String cabecalho = String.join(";", dadosCabecalhos) + "\n";
        String saldo = String.join(";", dadosSaldos) + "\n";

        dadosFinais.add(cabecalho);
        dadosFinais.add(saldo);

        FileOutputStream fos = new FileOutputStream(caminhoArquivo, true);
        ManipuladorArquivo.escreverArquivo(fos,dadosFinais);
    }
}
