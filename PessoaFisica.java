package Pessoa;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PessoaFisica {
    private long id;
    private String nome;
    private String cpf;
    private LocalDate dataNasc;
    private String telefone;
    private String endereco;
    private double poupanca, salario, gastosMensais;

    public PessoaFisica(String id, String nome, String cpf, String dataNasc, String telefone, String endereco,
        String poupanca, String salario, String gastosMensais){
        this.id = Long.parseLong(id);
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dataNasc = LocalDate.parse(dataNasc,formatter);

        this.poupanca = Double.parseDouble(poupanca);
        this.salario = Double.parseDouble(salario);
        this.gastosMensais = Double.parseDouble(gastosMensais);
    }
}