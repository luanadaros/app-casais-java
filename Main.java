import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Utilitarios.Cadastro;
import Utilitarios.ManipuladorArquivo;
import Relatorios.EstatisticaCasais;
import Relatorios.EstatisticaPrestadores;
import Relatorios.Planejamento;

public class Main {
    public static void main(String[] args) throws Exception {
        String pasta = args[0];
        Cadastro baseDeDados = new Cadastro();
        boolean excecao = false;
        
        List<String> dadosPessoas = new ArrayList<String>(), dadosLares = new ArrayList<String>(), dadosTarefas = new ArrayList<String>(),
        dadosCasamentos = new ArrayList<String>(), dadosFestas = new ArrayList<String>(), dadosCompras = new ArrayList<String>();

        try {
            ManipuladorArquivo.lerArquivo(pasta + "/pessoas.csv", dadosPessoas);
            ManipuladorArquivo.lerArquivo(pasta + "/lares.csv", dadosLares);
            ManipuladorArquivo.lerArquivo(pasta + "/tarefas.csv", dadosTarefas);
            ManipuladorArquivo.lerArquivo(pasta + "/casamentos.csv", dadosCasamentos);
            ManipuladorArquivo.lerArquivo(pasta + "/festas.csv", dadosFestas);
            ManipuladorArquivo.lerArquivo(pasta + "/compras.csv", dadosCompras);

        } catch (IOException e) {
            System.out.println("Erro de I/O");
            excecao = true;
        }

        if(!excecao){
            try {
                baseDeDados.cadastraPessoas(dadosPessoas);
                baseDeDados.cadastrarLares(dadosLares);
                baseDeDados.cadastrarCasamentos(dadosCasamentos);
                baseDeDados.cadastrarTarefas(dadosTarefas);
                baseDeDados.cadastrarFestas(dadosFestas);
                baseDeDados.cadastrarCompras(dadosCompras);
            } catch (Exception e) {
                System.out.println(e);
                excecao = true;
            }
        }

        if(!excecao){
            Scanner scanner = new Scanner(System.in);
            
            //leitura CPFs
             while(true){
                if(!scanner.hasNextLine()){
                    break;
                }

                String linha = scanner.nextLine();
                if(linha.isEmpty()){
                    break;
                }
                
                linha = linha.trim();
                String[] cpfs = linha.split(",");
                List<String[]> planejamento = Planejamento.calculaPlanejamento(baseDeDados, cpfs[0].trim(), cpfs[1].trim());
                Planejamento.imprimePlanejamento(planejamento);
            } 
            scanner.close();

            EstatisticaCasais.geraRelatorioCasais(pasta + "/3-estatistica-casais.csv", baseDeDados);
            EstatisticaPrestadores.geraRelatorioPrestadores(pasta + "/2-estatistica-prestadores.csv", baseDeDados);
        } else {
            ManipuladorArquivo.escreverArquivo(pasta + "/1-planejamento.csv", null);
            ManipuladorArquivo.escreverArquivo(pasta + "/2-estatisticas-prestadores.csv", null);
            ManipuladorArquivo.escreverArquivo(pasta + "/3-estatisticas-casais.csv", null);
        }        
    }
}
