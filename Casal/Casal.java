package Casal;
import java.math.BigInteger;

import Evento.Casamento;
import Evento.NovoLar;
import TipoPessoa.PessoaFisica;

public class Casal {
    private PessoaFisica p1;
    private PessoaFisica p2;
    private Casamento casamento = null;
    private NovoLar novoLar = null;

    public Casal(PessoaFisica p1, PessoaFisica p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public void setLar(NovoLar lar) {
        this.novoLar = lar;
    }

    public void setCasamento(Casamento casamento) {
        this.casamento = casamento;
    }

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

    public String[] getNomes(){
        String[] nomes = new String[2];

        nomes[0] = this.p1.getNome();
        nomes[1] = this.p2.getNome();

        return nomes;
    }

    public Casamento getCasamento(){
        return this.casamento;
    }

    public NovoLar getNovoLar(){
        return this.novoLar;
    }
}
