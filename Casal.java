import Evento.Casamento;
import Evento.NovoLar;
import TipoPessoa.PessoaFisica;

public class Casal {
    private PessoaFisica p1;
    private PessoaFisica p2;
    private Casamento casamento;
    private NovoLar novoLar;

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
}
