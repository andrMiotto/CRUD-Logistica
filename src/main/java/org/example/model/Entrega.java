package org.example.model;

import java.time.LocalDate;

public class Entrega {
    private int id;
    private int motoristaId;
    private int pedidoId;
    private LocalDate data_saida;
    private LocalDate data_entrada;
    private StatusEntrega status;

    public Entrega(int id, int motoristaId, int pedidoId, LocalDate data_saida, LocalDate data_entrada, StatusEntrega status) {
        this.id = id;
        this.motoristaId = motoristaId;
        this.pedidoId = pedidoId;
        this.data_saida = data_saida;
        this.data_entrada = data_entrada;
        this.status = status;
    }

    public Entrega(int motoristaId, int pedidoId, LocalDate data_saida, LocalDate data_entrada, StatusEntrega status) {
        this.motoristaId = motoristaId;
        this.pedidoId = pedidoId;
        this.data_saida = data_saida;
        this.data_entrada = data_entrada;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMotoristaId() {
        return motoristaId;
    }

    public void setMotoristaId(int motoristaId) {
        this.motoristaId = motoristaId;
    }

    public int getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }

    public LocalDate getData_saida() {
        return data_saida;
    }

    public void setData_saida(LocalDate data_saida) {
        this.data_saida = data_saida;
    }

    public LocalDate getData_entrada() {
        return data_entrada;
    }

    public void setData_entrada(LocalDate data_entrada) {
        this.data_entrada = data_entrada;
    }

    public StatusEntrega getStatus() {
        return status;
    }

    public void setStatus(StatusEntrega status) {
        this.status = status;
    }
}
