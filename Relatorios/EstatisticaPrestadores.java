package Relatorios;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.math.BigInteger;

import Atividades.Tarefa;
import Atividades.Compra;
import TipoPessoa.*;
import Utilitarios.Cadastro;
import Utilitarios.ConversorNumerico;
import Utilitarios.ManipuladorArquivo;

public class EstatisticaPrestadores {
    private static List<String[]> retornaListaPF(Map<PessoaFisica, Double>map){
        List<String[]> dados = new ArrayList<>();

        map.forEach((pf, valor) -> {
            String[] dado = new String[3];

            dado[0] = "PF";
            dado[1] = pf.getNome();
            dado[2] = Double.toString(valor);

            dados.add(dado);
        });

        //ordenacao da lista
        dados.sort((a, b) -> {
            double recebidoA = Double.parseDouble(a[2]);
            double recebidoB = Double.parseDouble(b[2]);
            
            if(recebidoA > recebidoB){
                return -1;
            } else if(recebidoA < recebidoB){
                return 1;
            } else {
                return a[1].compareTo(b[1]);
            }
        }); 

        return dados;
    }

    private static List<String[]> retornaListaPJ(Map<PessoaJuridica, Double>map){
        List<String[]> dados = new ArrayList<>();

        map.forEach((pj, valor) -> {
            String[] dado = new String[3];

            dado[0] = "PJ";
            dado[1] = pj.getNome();
            dado[2] = Double.toString(valor);

            dados.add(dado);
        });

        //ordenacao da lista
        dados.sort((a, b) -> {
            double recebidoA = Double.parseDouble(a[2]);
            double recebidoB = Double.parseDouble(b[2]);
            
            if(recebidoA > recebidoB){
                return -1;
            } else if(recebidoA < recebidoB){
                return 1;
            } else {
                return a[1].compareTo(b[1]);
            }
        }); 

        return dados;
    }

    private static List<String[]> retornaListaLoja(Map<Loja, Double>map){
        List<String[]> dados = new ArrayList<>();

        map.forEach((loja, valor) -> {
            String[] dado = new String[3];

            dado[0] = "Loja";
            dado[1] = loja.getNome();
            dado[2] = Double.toString(valor);

            dados.add(dado);
        });

        //ordenacao da lista
        dados.sort((a, b) -> {
            double recebidoA = Double.parseDouble(a[2]);
            double recebidoB = Double.parseDouble(b[2]);
            
            if(recebidoA > recebidoB){
                return -1;
            } else if(recebidoA < recebidoB){
                return 1;
            } else {
                return a[1].compareTo(b[1]);
            }
        }); 

        return dados;
    }

    private static List<String> retornaListaFinal(List<String[]> pf, List<String[]> pj, List<String[]> loja){
        List<String> dadosFinais = new ArrayList<>();

        for(String[] v: pf){
            v[2] = ConversorNumerico.converterParaMoedaBR(Double.parseDouble(v[2]));
            String resultado = String.join(";", v) + "\n";
            dadosFinais.add(resultado);
        }

        for(String[] v: pj){
            v[2] = ConversorNumerico.converterParaMoedaBR(Double.parseDouble(v[2]));
            String resultado = String.join(";", v) + "\n";
            dadosFinais.add(resultado);
        }

        for(String[] v: loja){
            v[2] = ConversorNumerico.converterParaMoedaBR(Double.parseDouble(v[2]));
            String resultado = String.join(";", v) + "\n";
            dadosFinais.add(resultado);
        }

        return dadosFinais;
    }

    public static void geraRelatorioPrestadores(String caminho_arquivo, Cadastro cadastro) throws Exception{
        Map<PessoaFisica, Double> prestadoresPF = new HashMap<>();
        Map<PessoaJuridica, Double> prestadoresPJ = new HashMap<>();
        Map<Loja, Double> prestadoresLoja = new HashMap<>();
        Map<BigInteger, Tarefa> tarefas = cadastro.getMapTarefas();
        Map<BigInteger, Compra> compras = cadastro.getMapCompras();
        Map<BigInteger, PessoaFisica> pessoasFisicas = cadastro.getMapPessoasFisicas();
        Map<BigInteger, PessoaJuridica> pessoasJuridicas = cadastro.getMapPessoasJuridicas();
        Map<BigInteger, Loja> lojas = cadastro.getMapLojas();

        // analisa map de tarefas, armazenando o valor de cada tarefa realizada por cada prestador de cada tipo
        for(Tarefa t: tarefas.values()){
            BigInteger idPrestador = t.getIdPrestador();

            if(pessoasFisicas.containsKey(idPrestador)){
                PessoaFisica pf = pessoasFisicas.get(idPrestador);
                Double valorAReceber = t.getValorPrestador();

                if(prestadoresPF.containsKey(pf)){
                    prestadoresPF.replace(pf, prestadoresPF.get(pf) + valorAReceber);
                } else {
                    prestadoresPF.put(pf, valorAReceber);
                }

            } else if(pessoasJuridicas.containsKey(idPrestador)){
                PessoaJuridica pj = pessoasJuridicas.get(idPrestador);
                Double valorAReceber = t.getValorPrestador();

                if(prestadoresPJ.containsKey(pj)){
                    prestadoresPJ.replace(pj, prestadoresPJ.get(pj) + valorAReceber);
                } else {
                    prestadoresPJ.put(pj, valorAReceber);
                }

            }
        }

        // analisa map de compras, armazenando o valor de cada compra realizada em cada loja
        for(Compra c: compras.values()){
            BigInteger idLoja = c.getIdLoja();

            if(lojas.containsKey(idLoja)){
                Loja loja = lojas.get(idLoja);
                Double valorAReceber = c.getValorTotal();

                if(prestadoresLoja.containsKey(loja)){
                    prestadoresLoja.replace(loja, prestadoresLoja.get(loja) + valorAReceber);
                } else {
                    prestadoresLoja.put(loja, valorAReceber);
                }
            }
        }

        for(Loja l: lojas.values()){
            if(!prestadoresLoja.containsKey(l)){
                prestadoresLoja.put(l, 0.0);
            }
        }

        for(PessoaJuridica pj: pessoasJuridicas.values()){
            if(!prestadoresPJ.containsKey(pj)){
                prestadoresPJ.put(pj, 0.0);
            }
        }

        //formata dados para impressao correta no arquivo de relatorio
        List<String> dadosFinais = retornaListaFinal(retornaListaPF(prestadoresPF), retornaListaPJ(prestadoresPJ), retornaListaLoja(prestadoresLoja));

        ManipuladorArquivo.escreverArquivo(caminho_arquivo, dadosFinais);
    }

}
