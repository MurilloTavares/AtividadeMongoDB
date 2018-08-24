package com.ifpb.models;

public class ItemVenda {
    
    private Produto produto;
    private int quatidade;

    // Obrigatorio
    public ItemVenda() {
    }

    public ItemVenda(Produto produto, int quatidade) {
        this.produto = produto;
        this.quatidade = quatidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuatidade() {
        return quatidade;
    }

    public void setQuatidade(int quatidade) {
        this.quatidade = quatidade;
    }

    @Override
    public String toString() {
        return "ItemVenda{" + "produto=" + produto + ", quatidade=" + quatidade + '}';
    }
    
}
