package TipoPessoa;

import java.math.BigInteger;
import java.text.ParseException;

public class Loja extends PessoaJuridica{
    public Loja(String id, String nome, String telefone, String endereco, String cnpj) throws ParseException{
        super(id, nome, telefone, endereco, cnpj);
    }
    
    public String getNome(){
        return super.getNome();
    }

    public BigInteger getId(){
        return super.id;
    }
}

