package Casal;

import Evento.Casamento;
import Evento.NovoLar;
import TipoPessoa.PessoaFisica;
import java.time.LocalDate;

public class Casal {
    private PessoaFisica p1;
    private PessoaFisica p2;
    private Casamento casamento = null;
    private NovoLar novoLar = null;

    public Casal(PessoaFisica p1, PessoaFisica p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    //sets
    public void setLar(NovoLar lar) {
        this.novoLar = lar;
    }

    public void setCasamento(Casamento casamento) {
        this.casamento = casamento;
    }

    public void atualizaPoupancaCasal(Double valor){
        this.p1.atualizaPoupanca(valor/2);
        this.p2.atualizaPoupanca(valor/2);
    }

    public void atualizaPoupancaCasalRendimento(){
        this.p1.atualizaPoupancaRendimento();
        this.p2.atualizaPoupancaRendimento();
    }

    //gets
    public String[] getNomes(){
        String[] nomes = new String[2];

        nomes[0] = this.p1.getNome();
        nomes[1] = this.p2.getNome();

        return nomes;
    }

    public String[] getCPFs(){
        String[] cpfs = new String[2];

        cpfs[0] = this.p1.getCPF();
        cpfs[1] = this.p2.getCPF();

        return cpfs;
    }

    public Casamento getCasamento(){
        return this.casamento;
    }

    public NovoLar getNovoLar(){
        return this.novoLar;
    }

    public LocalDate getMenorDataInicioTarefas(){
        if(this.temTarefas()){
            return this.novoLar.getMenorDataInicio();
        }
        return null;
    }

    public LocalDate getDataInicioPlanejamento(){
        LocalDate menorData = LocalDate.MAX;

        if(this.temFesta()){
            if(this.casamento.getDataFesta().isBefore(menorData)){
                menorData = this.casamento.getDataFesta();
            }
        }

        if(this.temTarefas()){
            if(this.novoLar.getMenorDataInicio().isBefore(menorData)){
                menorData = this.novoLar.getMenorDataInicio();
            }
        }

        return menorData;
    }

    public LocalDate getDataFesta(){
        if(this.temFesta()){
            return this.casamento.getDataFesta();
        }
        return null;
    }

    //verificacoes
    public boolean temCasamento() {
        if(this.casamento == null){
            return false;
        }

        return true;
    }

    public boolean temNovoLar() {
        if(this.novoLar == null){
            return false;
        }

        return true;
    }

    public boolean temFesta(){
        if(this.casamento == null){
            return false;
        }

        return this.casamento.temFesta();
    }

    public boolean temTarefas(){
        if(this.novoLar == null){
            return false;
        }

        return this.novoLar.temTarefas();
    }

    public boolean contasEstaoPagas(){
        boolean condicao1 = true;
        boolean condicao2 = true;

        if(this.temTarefas()){
            condicao1 = this.novoLar.tarefasEstaoPagas() && this.novoLar.comprasEstaoPagas();
        } 

        if(this.temFesta()){
            condicao2 = this.casamento.festaEstaPaga();
        }

        return condicao1 && condicao2;
    }
    
    //calculos
    public Double calculaGastosCasalNoMes(LocalDate data){
        Double gastos = 0.0;
        
        // calcula apenas os gastos excepcionais, ou seja, somente os gastos que nao estao previstos nos gastos mensais do casal
        if(this.temNovoLar()){
            gastos += this.novoLar.getGastosMensais(data);
        }
        if(this.temCasamento()){
            gastos += this.casamento.getGastosMensais(data);
        }

        return gastos;
    }

    public Double calculaSaldoCasalNoMes(LocalDate data, Double gastos){
        int mes = data.getMonthValue();

        //adiciona gastos mensais no calculo
        gastos += this.p1.getGastosMensais();
        gastos += this.p2.getGastosMensais();

        Double contaP1 = this.p1.getPoupanca() + this.p1.getSalario() + this.p1.getDecimoTerceiroSalario(mes);
        Double contaP2 = this.p2.getPoupanca() + this.p2.getSalario() + this.p2.getDecimoTerceiroSalario(mes);

        Double saldo = contaP1 + contaP2 - gastos;

        this.atualizaPoupancaCasal(saldo);

        return saldo;
    }

}
