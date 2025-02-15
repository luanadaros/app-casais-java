package TipoPessoa;

import java.math.BigInteger;

public class PessoaJuridica {
    protected BigInteger id;
    protected String nome;
    protected String telefone;
    protected String endereco;
    protected String cnpj;

    public PessoaJuridica(String id, String nome, String telefone, String endereco, String cnpj) {
        this.id = new BigInteger(id);
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cnpj = cnpj;
    }

    public void imprimePessoaJuridica(){
        System.out.println("PJ: ID - " + id + ", Nome - " + nome + ", Telefone - " + telefone + ", Endereço - " + endereco + ", CNPJ - " + cnpj);
    }

    public static boolean comparaCNPJ(PessoaJuridica p1, PessoaJuridica p2){
        if(p1.cnpj == p2.cnpj){
            return true;
        } else {
            return false;
        }
    }
}
