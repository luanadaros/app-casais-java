package Relatorios;

import java.util.List;
import java.util.ArrayList;
import Casal.Casal;
import Utilitarios.Cadastro;
import java.math.BigInteger;
import java.util.Map;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Planejamento {
    public static List<String[]> calculaPlanejamento(Cadastro cadastro, String cpf1, String cpf2){
        Map<BigInteger, Casal> casais = cadastro.getMapCasais();
        List<String[]> saldos = new ArrayList<String[]>();
        boolean casalEncontrado = false;
        Casal casal = null;

        //procura casal com CPFs correspondentes
        for(Casal c: casais.values()){
            String[] cpfs = c.getCPFs();

            if((cpfs[0].equals(cpf1) && cpfs[1].equals(cpf2)) || (cpfs[0].equals(cpf2) && cpfs[1].equals(cpf1))){
                casalEncontrado = true;
                casal = c;
                break;
            }
        }

        if(!casalEncontrado){
            System.out.println("Casal com CPFs " + cpf1 + " e " + cpf2 + " não está cadastrado.");
        } else {
            if(!casal.temFesta() && !casal.temTarefas()){
                System.out.println("Casal com CPFs " + cpf1 + " e " + cpf2 + " não possui gastos cadastrados.");
            } else {
                LocalDate dataInicio = casal.getDataInicioPlanejamento();
                LocalDate dataAtual = dataInicio;
                DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("MM/yyyy");
                casal.atualizaPoupancaCasalRendimento();
                
                while(true){
                    if(casal.calculaGastosTarefasDoCasalNoMes(dataAtual) == 0.0){
                        break;
                    }

                    Double saldo = casal.calculaSaldoCasalNoMes(dataAtual);
                    String[] saldoMes = new String[2];
                    saldoMes[0] = dataAtual.format(formatoData);
                    saldoMes[1] = String.format("%.2f", saldo);
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
}
