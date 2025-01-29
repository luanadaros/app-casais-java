import java.util.Scanner;

import Pessoa.PessoaFisica;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        List<String[]> dadosPessoas = ManipuladorCSV.lerCSV("pessoas.csv");
        List<PessoaFisica> pessoasFisicasCadastradas = new ArrayList<>();

        for(String[] dado : dadosPessoas) {
            if(dado[1].equals("F")){
                //PessoaFisica pessoa = new PessoaFisica();
                //pessoasFisicasCadastradas.add(pessoa);
            } else {

            }
        }
    }
}
