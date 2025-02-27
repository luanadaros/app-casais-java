package Utilitarios;
import TipoPessoa.*;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Atividades.*;
import Casal.Casal;
import Evento.*;

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

    // add
    private void adicionarPessoaFisica(BigInteger id, PessoaFisica pessoa) {
        pessoasFisicas.put(id, pessoa);
    }

    private void adicionarPessoaJuridica(BigInteger id, PessoaJuridica pessoa) {
        pessoasJuridicas.put(id, pessoa);
    }

    private void adicionarLoja(BigInteger id, Loja loja){
        lojas.put(id, loja);
    }

    private void adicionarCasal(BigInteger idPessoa1, BigInteger idPessoa2, Casal casal) {
        casais.put(idPessoa1, casal);
        casais.put(idPessoa2, casal);
    }

    private void adicionarLar(BigInteger id, NovoLar lar) {
        lares.put(id, lar);
    }

    private void adicionarCasamento(BigInteger id, Casamento casamento) {
        casamentos.put(id, casamento);
    }

    private void adicionarTarefa(BigInteger id, Tarefa tarefa) {
        tarefas.put(id, tarefa);
    }

    private void adicionarFesta(BigInteger id, Festa festa) {
        festas.put(id, festa);
    }

    private void adicionarCompra(BigInteger id, Compra compra) {
        compras.put(id, compra);
    }

    //get 
    private PessoaFisica getPessoaFisica(BigInteger id) {
        return pessoasFisicas.get(id);
    }

    private Casal getCasal(BigInteger id) {
        return casais.get(id);
    }

    public Map<BigInteger, Casal> getMapCasais(){
        return this.casais;
    }

    public Map<BigInteger, Casamento> getMapCasamentos(){
        return this.casamentos;
    }

    public Map<BigInteger, Tarefa> getMapTarefas(){
        return this.tarefas;
    }

    public Map<BigInteger, Compra> getMapCompras(){
        return this.compras;
    }

    public Map<BigInteger, PessoaFisica> getMapPessoasFisicas(){
        return this.pessoasFisicas;
    }

    public Map<BigInteger, PessoaJuridica> getMapPessoasJuridicas(){
        return this.pessoasJuridicas;
    }

    public Map<BigInteger, Loja> getMapLojas(){
        return this.lojas;
    }

    public Casal getCasalPorCPFs(String cpf1, String cpf2){
        for(Casal c: casais.values()){
            String[] cpfs = c.getCPFs();
            if((cpfs[0].equals(cpf1) && cpfs[1].equals(cpf2)) || (cpfs[0].equals(cpf2) && cpfs[1].equals(cpf1))){
                return c;
            }
        }

        return null;
    }
    
    //verificacao 
    private boolean verificaIDPessoas(BigInteger id){
        if(this.pessoasFisicas.containsKey(id) || this.pessoasJuridicas.containsKey(id) || this.lojas.containsKey(id)){
            return true;
        }

        return false;
    }

    private boolean verificaIDLares(BigInteger id){
        if(this.lares.containsKey(id)){
            return true;
        }

        return false;
    }

    private boolean verificaIDCasamentos(BigInteger id){
        if(this.casamentos.containsKey(id)){
            return true;
        }

        return false;
    }

    private boolean verificaIDTarefas(BigInteger id){
        if(this.tarefas.containsKey(id)){
            return true;
        }

        return false;
    }

    private boolean verificaIDFestas(BigInteger id){
        if(this.festas.containsKey(id)){
            return true;
        }

        return false;
    }

    private boolean verificaIDCompras(BigInteger id){
        if(this.compras.containsKey(id)){
            return true;
        }

        return false;
    }

    private boolean verificaCPF(PessoaFisica p1){
        for(PessoaFisica p2: this.pessoasFisicas.values()){
            if(PessoaFisica.comparaCPF(p1,p2)){
                return true;
            }
        }
        return false;
    }

    private boolean verificaCNPJ(PessoaJuridica p1){
        for(PessoaJuridica p2: this.pessoasJuridicas.values()){
            if(PessoaJuridica.comparaCNPJ(p1,p2)){
                return true;
            }
        }
        return false;
    }

    //cadastramento
    public void cadastraPessoas(List<String> dados) throws Exception{
        for(String pessoa: dados){
            pessoa.trim();
            String[] itens = pessoa.split(";");
            BigInteger id = new BigInteger(itens[0]);

            if(verificaIDPessoas(id)){
                throw new Exception("ID repetido " + id + " na classe Pessoa.");
            } else {
                if(itens[1].equals("F")){
                    //pessoa fisica
                    PessoaFisica pf = new PessoaFisica(itens[0], itens[2], itens[3], itens[4], itens[5], itens[6], itens[7], itens[8], itens[9]);
                    if(verificaCPF(pf)){
                        throw new Exception("O CPF " + itens[5] + " da Pessoa " + id + " é repetido.");
                    } else {
                        this.adicionarPessoaFisica(id, pf);
                    }

                } else if(itens[1].equals("J")){
                    //pessoa juridica
                    PessoaJuridica pj = new PessoaJuridica(itens[0], itens[2], itens[3], itens[4], itens[5]);
                    if(verificaCNPJ(pj)){
                        throw new Exception("O CNPJ " + itens[5] + " da Pessoa " + id + " é repetido.");
                    } else {
                        this.adicionarPessoaJuridica(id, pj);
                    }
                } else {
                    //loja
                    Loja loja = new Loja(itens[0], itens[2], itens[3], itens[4], itens[5]);
                    this.adicionarLoja(id, loja);
                }
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

            if(verificaIDLares(idLar)){
                throw new Exception("ID repetido " + idLar + " na classe Lar.");
            } else {
                //verificar se p1 e p2 estao cadastrados em pessoas fisicas
                if(!verificaIDPessoas(idP1)){
                    throw new Exception("ID(s) de Pessoa " + idP1 + " não cadastrado no Lar de ID " + idLar + ".");
                } else if(!verificaIDPessoas(idP2)){
                    throw new Exception("ID(s) de Pessoa " + idP1 + " não cadastrado no Lar de ID " + idLar + ".");
                } else if(!verificaIDPessoas(idP1) && !verificaIDPessoas(idP2)) {
                    throw new Exception("ID(s) de Pessoa " + idP1 + " " + idP2 + " não cadastrado no Lar de ID " + idLar + ".");
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
                    
                    try {
                        NovoLar novoLar = new NovoLar(itens[0], itens[3], itens[4], itens[5]);
                        casal.setLar(novoLar);
                        this.adicionarLar(idLar, novoLar);
                    } catch (ParseException e){
                        throw new Exception("Erro de formatação");
                    }
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

            if(verificaIDCasamentos(idCasamento)){
                throw new Exception("ID repetido " + idCasamento + " na classe Casamento.");
            } else {
                //verificar se p1 e p2 estao cadastrados 
                if(!verificaIDPessoas(idP1)){
                    throw new Exception("ID(s) de Pessoa " + idP1 + " não cadastrado no Casamento de ID " + idCasamento + ".");
                } else if(!verificaIDPessoas(idP2)){
                    throw new Exception("ID(s) de Pessoa " + idP1 + " não cadastrado no Casamento de ID " + idCasamento + ".");
                } else if(!verificaIDPessoas(idP1) && !verificaIDPessoas(idP2)) {
                    throw new Exception("ID(s) de Pessoa " + idP1 + " " + idP2 + " não cadastrado no Casamento de ID " + idCasamento + ".");
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
                    
                    try {
                        Casamento novoCasamento = new Casamento(itens[0], itens[3], itens[4], itens[5]);
                        casal.setCasamento(novoCasamento);
                        this.adicionarCasamento(idCasamento, novoCasamento);   
                    } catch (ParseException e){
                        throw new Exception("Erro de formatação");
                    }
                }
            }

        }
    }

    public void cadastrarTarefas(List<String> dados) throws Exception{
        for(String tarefa: dados){
            String[] itens = tarefa.split(";");
            BigInteger idTarefa = new BigInteger(itens[0]);
            BigInteger idLar = new BigInteger(itens[1]);
            BigInteger idPrestador = new BigInteger(itens[2]);

            if(verificaIDTarefas(idTarefa)){
                throw new Exception("ID repetido " + idTarefa + " na classe Tarefa.");
            } else {
                if(!verificaIDLares(idLar)){
                    throw new Exception("ID(s) de Lar " + idLar + " não cadastrado na Tarefa de ID " + idTarefa + ".");
                } else if(!verificaIDPessoas(idPrestador)){
                    throw new Exception("ID(s) de Prestador de Serviço " + idPrestador + " não cadastrado na Tarefa de ID " + idTarefa + ".");
                } else {
                    NovoLar lar = lares.get(idLar);
                    try {
                        Tarefa novaTarefa = new Tarefa(itens[0], itens[2], itens[3], itens[4], itens[5], itens[6]);
                        lar.adicionarTarefa(novaTarefa);
                        this.adicionarTarefa(idTarefa, novaTarefa);
                    } catch (ParseException e) {
                        throw new Exception("Erro de formatação");
                    }
                }
            }
        }

    } 

    public void cadastrarFestas(List<String> dados) throws Exception {
        for(String festa: dados){
            String[] itens = festa.split(";");
            BigInteger idFesta = new BigInteger(itens[0]);
            BigInteger idCasamento = new BigInteger(itens[1]);

            if(verificaIDFestas(idFesta)){
                throw new Exception("ID repetido " + idFesta + " na classe Festa.");
            } else {
                if(!verificaIDCasamentos(idCasamento)){
                    throw new Exception("ID(s) de Casamento " + idCasamento + " não cadastrado na Festa de ID " + idFesta + ".");
                } else {
                    Casamento casamento = casamentos.get(idCasamento);

                    try {
                        Festa novaFesta = new Festa(itens[0], itens[2], itens[3], itens[4], itens[5], itens[6], itens[7]);
                        int numConvidados = Integer.parseInt(itens[7]);
    
                        for(int i = 8; i < numConvidados + 8; i++){
                            novaFesta.adicionarConvidado(itens[i]);
                        }
    
                        casamento.setFesta(novaFesta);
                        this.adicionarFesta(idFesta, novaFesta);
                    } catch (ParseException e) {
                        throw new Exception("Erro de formatação");
                    }
                }
            }
        }
    }

    public void cadastrarCompras(List<String> dados) throws Exception {
        for(String compra: dados){
            String[] itens = compra.split(";");
            BigInteger idCompra = new BigInteger(itens[0]);
            BigInteger idTarefa = new BigInteger(itens[1]);
            BigInteger idLoja = new BigInteger(itens[2]);

            if(verificaIDCompras(idCompra)){
                throw new Exception("ID repetido " + idCompra + " na classe Compra.");
            } else {
                if(!verificaIDTarefas(idTarefa)){
                    throw new Exception("ID(s) de Tarefa " + idTarefa + " não cadastrado na Compra de ID " + idCompra + ".");
                } else if(!this.lojas.containsKey(idLoja)){
                    throw new Exception("ID(s) de Loja " + idLoja + " não cadastrado na Compra de ID " + idCompra + ".");
                } else if(this.pessoasJuridicas.containsKey(idLoja)){
                    throw new Exception("ID " + idLoja + " da Compra de ID " + idCompra + " não se refere a uma Loja, mas a uma PJ.");
                } else {
                    Tarefa tarefa = tarefas.get(idTarefa);

                    try {
                        Compra novaCompra = new Compra(itens[0], itens[2], itens[3], itens[4], itens[5], itens[6]);
                        tarefa.adicionarCompra(novaCompra);    
                        this.adicionarCompra(idCompra, novaCompra);
                    } catch (ParseException e) {
                        throw new Exception("Erro de formatação");
                    }
                }
            }
        }
    }


}
