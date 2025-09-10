package org.example.model;

public enum StatusEntrega {
    EM_ROTA("Em rota"),
    ENTREGUE("Entregue"),
    ATRASADA("Atrasada");


    private String descricao;

    StatusEntrega(String descricao) {
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


