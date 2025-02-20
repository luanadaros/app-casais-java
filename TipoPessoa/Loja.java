package TipoPessoa;

import java.text.ParseException;

public class Loja extends PessoaJuridica{
    public Loja(String id, String nome, String telefone, String endereco, String cnpj) throws ParseException{
        super(id, nome, telefone, endereco, cnpj);
    }
    
}
