package TipoPessoa;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.math.BigInteger;
import java.text.ParseException;

import Utilitarios.ConversorNumerico;

public class PessoaFisica {
    private BigInteger id;
    private String nome;
    private String cpf;
    private LocalDate dataNasc;
    private String telefone;
    private String endereco;
    private double poupanca, salario, gastosMensais;

    public PessoaFisica(String id, String nome, String telefone, String endereco, String cpf, String dataNasc,
        String poupanca, String salario, String gastosMensais) throws ParseException{
        this.id = new BigInteger(id);
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dataNasc = LocalDate.parse(dataNasc,formatter);
        try {
            this.poupanca = ConversorNumerico.converterParaDouble(poupanca);
            this.salario = ConversorNumerico.converterParaDouble(salario);
            this.gastosMensais = ConversorNumerico.converterParaDouble(gastosMensais);
        } catch (ParseException e){
            System.out.println(e);
        }
    }

    public void imprimePessoaFisica(){
        System.out.println("PF: ID - " + id + ", Nome - " + nome + ", CPF - " + cpf + ", Data de Nascimento - " + dataNasc + ", Telefone - " + telefone + ", Endereço - " + endereco + ", Poupança - " + poupanca + ", Salário - " + salario + ", Gastos Mensais - " + gastosMensais);
    }

    public static boolean comparaCPF(PessoaFisica p1, PessoaFisica p2){
        if(p1.cpf == p2.cpf){
            return true;
        } else{
            return false;
        }
    }

    public String getNome(){
        return nome;
    }
}