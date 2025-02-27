package Atividades;
import java.math.BigInteger;
import java.text.ParseException;

import Utilitarios.ConversorNumerico;

public class Compra {
    private BigInteger id;
    private BigInteger idLoja;
    private String nomeProduto;
    private int qtdProduto;
    private Double precoUnitario;
    private int numParcelas;
    private int qtdParcelasPagas = 0;

    public Compra(String id, String idLoja, String nomeProduto, String qtdProduto, String precoUnitario, String numParcelas)
    throws ParseException {
        this.id = new BigInteger(id);
        this.idLoja = new BigInteger(idLoja);
        this.nomeProduto = nomeProduto;
        this.qtdProduto = Integer.parseInt(qtdProduto);

        try {
            this.precoUnitario = ConversorNumerico.converterParaDouble(precoUnitario);
        } catch (ParseException e){
            System.out.println(e);
        }

        this.numParcelas = Integer.parseInt(numParcelas);
    }

    //gets
    public Double getValorTotal(){
        return precoUnitario * qtdProduto;
    }

    public BigInteger getIdLoja(){
        return this.idLoja;
    }

    public Double getValorParcela(){
        Double valorTotal = getValorTotal();
        return (valorTotal / numParcelas);
    }

    public int getNumParcelas(){
        return numParcelas;
    }

    public int getQtdParcelasPagas(){
        return qtdParcelasPagas;
    }

    public BigInteger getId(){
        return this.id;
    }

    public void pagarParcela(){
        if(qtdParcelasPagas < numParcelas){
            qtdParcelasPagas++;
        }
    }

    //verificacoes
    public boolean compraEstaPaga(){
        if(qtdParcelasPagas == numParcelas){
            return true;
        }
        return false;
    }
}
