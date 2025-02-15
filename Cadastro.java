import Evento.*;
import Organizacao.*;
import TipoPessoa.*;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cadastro {
    private final Map<BigInteger, PessoaFisica> pessoasFisicas = new HashMap<>();
    private final Map<BigInteger, PessoaJuridica> pessoasJuridicas = new HashMap<>();
    private final Map<BigInteger, Loja> lojas = new HashMap<>();
    private final Map<BigInteger, Casal> casais = new HashMap<>();
    private final Map<BigInteger, NovoLar> lares = new HashMap<>();
    private final Map<BigInteger, Casamento> casamentos = new HashMap<>();
    private final Map<BigInteger, Tarefa> tarefas = new HashMap<>();
    private final Map<BigInteger, Festa> festas = new HashMap<>();
    private final Map<BigInteger, Compra> compras = new HashMap<>();

    public void adicionarPessoaFisica(BigInteger id, PessoaFisica pessoa) {
        pessoasFisicas.put(id, pessoa);
    }

    public void adicionarPessoaJuridica(BigInteger id, PessoaJuridica pessoa) {
        pessoasJuridicas.put(id, pessoa);
    }

    public void adicionarLoja(BigInteger id, Loja loja){
        lojas.put(id, loja);
    }

    public void adicionarCasal(BigInteger idPessoa1, BigInteger idPessoa2, Casal casal) {
        casais.put(idPessoa1, casal);
        casais.put(idPessoa2, casal);
    }

    public void adicionarLar(BigInteger id, NovoLar lar) {
        lares.put(id, lar);
    }

    public void adicionarCasamento(BigInteger id, Casamento casamento) {
        casamentos.put(id, casamento);
    }

    public PessoaFisica getPessoaFisica(BigInteger id) {
        return pessoasFisicas.get(id);
    }

    public Casal getCasal(BigInteger id) {
        return casais.get(id);
    }

    private boolean verificaIDPessoas(BigInteger id){
        if(this.pessoasFisicas.containsKey(id) || this.pessoasJuridicas.containsKey(id) || this.lojas.containsKey(id)){
            return false;
        }

        return true;
    }

    private boolean verificaIDLares(BigInteger id){
        if(this.lares.containsKey(id)){
            return false;
        }

        return true;
    }

    private boolean verificaIDCasamentos(BigInteger id){
        if(this.casamentos.containsKey(id)){
            return false;
        }

        return true;
    }

    private boolean verificaIDTarefas(BigInteger id){
        if(this.tarefas.containsKey(id)){
            return false;
        }

        return true;
    }

    private boolean verificaIDFestas(BigInteger id){
        if(this.festas.containsKey(id)){
            return false;
        }

        return true;
    }

    private boolean verificaIDCompras(BigInteger id){
        if(this.compras.containsKey(id)){
            return false;
        }

        return true;
    }

    private boolean verificaCPF(PessoaFisica p1){
        for(PessoaFisica p2: this.pessoasFisicas.values()){
            if(PessoaFisica.comparaCPF(p1,p2)){
                return false;
            }
        }
        return true;
    }

    private boolean verificaCNPJ(PessoaJuridica p1){
        for(PessoaJuridica p2: this.pessoasJuridicas.values()){
            if(PessoaJuridica.comparaCNPJ(p1,p2)){
                return false;
            }
        }
        return true;
    }

    public void cadastraPessoas(List<String> dados) throws Exception{
        for(String pessoa: dados){
            String[] itens = pessoa.split(";");
            BigInteger id = new BigInteger(itens[0]);

            if(!verificaIDPessoas(id)){
                throw new Exception("ID repetido " + id + " na classe Pessoa");
            }

            if(itens[1].equals("F")){
                //pessoa fisica
                PessoaFisica pf = new PessoaFisica(itens[0], itens[2], itens[3], itens[4], itens[5], itens[6], itens[7], itens[8], itens[9]);
                if(!verificaCPF(pf)){
                    throw new Exception("O CPF " + itens[5] + " da Pessoa " + id + " é repetido");
                }
                this.adicionarPessoaFisica(id, pf);
            } else if(itens[1].equals("J")){
                //pessoa juridica
                PessoaJuridica pj = new PessoaJuridica(itens[0], itens[2], itens[3], itens[4], itens[5]);
                if(!verificaCNPJ(pj)){
                    throw new Exception("O CNPJ " + itens[5] + " da Pessoa " + id + " é repetido");
                }

                this.adicionarPessoaJuridica(id, pj);
            } else {
                //loja
                Loja loja = new Loja(itens[0], itens[2], itens[3], itens[4], itens[5]);
                this.adicionarLoja(id, loja);
            }
        }
    }

    public void cadastrarLares(List<String> dados) throws Exception{
        for(String lar: dados){
            String[] itens = lar.split(";");
            BigInteger idLar = new BigInteger(itens[0]);
            BigInteger idP1 = new BigInteger(itens[1]);
            BigInteger idP2 = new BigInteger(itens[2]);
            Casal casal;

            if(!verificaIDLares(idLar)){
                throw new Exception("ID repetido " + idLar + " na classe Lar");
            } else {
                //verificar se p1 e p2 estao cadastrados em pessoas fisicas
                if(verificaIDPessoas(idP1)){
                    throw new Exception("ID de Pessoa " + idP1 + " não cadastrado no Lar de ID " + idLar);
                } else if(verificaIDPessoas(idP2)){
                    throw new Exception("ID de Pessoa " + idP1 + " não cadastrado no Lar de ID " + idLar);
                } else if(verificaIDPessoas(idP1) && verificaIDPessoas(idP2)) {
                    throw new Exception("IDs de Pessoa " + idP1 + " " + idP2 + " não cadastrado no Lar de ID " + idLar);
                } else {
                    //verifica se o casal ja foi cadastrado em casais
                    if(getCasal(idP1) == null && getCasal(idP2) == null){
                        PessoaFisica p1 = getPessoaFisica(idP1);
                        PessoaFisica p2 = getPessoaFisica(idP2);
        
                        casal = new Casal(p1, p2);
                        this.adicionarCasal(idP1, idP2, casal);
                    } else {
                        casal = getCasal(idP1);
                    }
        
                    NovoLar novoLar = new NovoLar(itens[0], itens[3], itens[4], itens[5]);
                    casal.setLar(novoLar);
                    this.adicionarLar(idLar, novoLar);
                }
            }


        }
    }
    
    public void cadastrarCasamentos(List<String> dados) throws Exception{
        for(String casamento: dados){
            String[] itens = casamento.split(";");
            BigInteger idCasamento = new BigInteger(itens[0]);
            BigInteger idP1 = new BigInteger(itens[1]);
            BigInteger idP2 = new BigInteger(itens[2]);
            Casal casal;

            if(!verificaIDCasamentos(idCasamento)){
                throw new Exception("ID repetido " + idCasamento + " na classe Casamento");
            } else {
                //verificar se p1 e p2 estao cadastrados 
                if(verificaIDPessoas(idP1)){
                    throw new Exception("ID de Pessoa " + idP1 + " não cadastrado no Casamento de ID " + idCasamento);
                } else if(verificaIDPessoas(idP2)){
                    throw new Exception("ID de Pessoa " + idP1 + " não cadastrado no Casamento de ID " + idCasamento);
                } else if(verificaIDPessoas(idP1) && verificaIDPessoas(idP2)) {
                    throw new Exception("IDs de Pessoa " + idP1 + " " + idP2 + " não cadastrado no Casamento de ID " + idCasamento);
                } else {
                    //verifica se o casal ja foi cadastrado em casais
                    if(getCasal(idP1) == null && getCasal(idP2) == null){
                        PessoaFisica p1 = getPessoaFisica(idP1);
                        PessoaFisica p2 = getPessoaFisica(idP2);
        
                        casal = new Casal(p1, p2);
                        this.adicionarCasal(idP1, idP2, casal);
                    } else {
                        casal = getCasal(idP1);
                    }
        
                    Casamento novoCasamento = new Casamento(itens[0], itens[3], itens[4], itens[5]);
                    casal.setCasamento(novoCasamento);
                    this.adicionarCasamento(idCasamento, novoCasamento);
                }
            }

        }
    }
}
