package Organizacao;
import java.math.BigInteger;
import java.text.ParseException;

import Utilitarios.ConversorNumerico;

public class Compra {
    private BigInteger id;
    private BigInteger idLoja;
    private String nomeProduto;
    private int qtdProduto;
    private double precoUnitario;
    private int numParcelas;

    public Compra(String id, String idLoja, String nomeProduto, String qtdProduto, String precoUnitario, String numParcelas)
    throws Exception {
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
}
