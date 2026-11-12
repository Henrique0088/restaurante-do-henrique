package br.com.senai.deliveryavalicao.dto;

import java.math.BigDecimal;

public class ProdutoRequest {

    private String nome;
    private BigDecimal preco;
    private Integer restauranteId;

    public ProdutoRequest() {
    }

    public ProdutoRequest(String nome, BigDecimal preco, Integer restauranteId) {
        this.nome = nome;
        this.preco = preco;
        this.restauranteId = restauranteId;
    }

   
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer  getRestauranteId() {
        return restauranteId;
    }

    public void setRestauranteId(Integer restauranteId) {
        this.restauranteId = restauranteId;
    }
}

