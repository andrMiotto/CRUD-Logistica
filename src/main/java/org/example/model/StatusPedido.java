package org.example.model;

public enum StatusPedido {
    PENDENTE("Pendente"),
    ENTREGUE("Entregue"),
    CANCELADO("Cancelado");

    private String descricao;

    StatusPedido(String descricao) {
        this.descricao = descricao;

    }

    public String getDescricao() {
        return descricao;

    }

    @Override
    public String toString() {
        return descricao;

    }
}
