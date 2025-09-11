package org.example.model;

import java.time.LocalDate;

public class Entrega {
    private int id;
    private int motoristaId;
    private int pedidoId;
    private LocalDate data_saida;
    private LocalDate data_entrega;
    private StatusEntrega status;


    public Entrega(int pedidoId, int motoristaId) {
        this.pedidoId = pedidoId;
        this.motoristaId = motoristaId;
        this.data_saida = LocalDate.now();
        this.data_entrega = null;
        this.status = StatusEntrega.EM_ROTA;
    }

    public Entrega(int id, int motoristaId, int pedidoId, LocalDate data_saida, LocalDate data_entrega, StatusEntrega status) {
        this.id = id;
        this.motoristaId = motoristaId;
        this.pedidoId = pedidoId;
        this.data_saida = data_saida;
        this.data_entrega = data_entrega;
        this.status = status;
    }

    public Entrega(int motoristaId, int pedidoId, LocalDate data_saida, LocalDate data_entrega, StatusEntrega status) {
        this.motoristaId = motoristaId;
        this.pedidoId = pedidoId;
        this.data_saida = data_saida;
        this.data_entrega = data_entrega;
        this.status = status;
    }

    public Entrega(int pedidoId, LocalDate now, String novoStatus) {
    }

    public Entrega() {

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

    public LocalDate getData_entrega() {
        return data_entrega;
    }

    public void setData_entrega(LocalDate data_entrega) {
        this.data_entrega = data_entrega;
    }

    public StatusEntrega getStatus() {
        return status;
    }

    public void setStatus(StatusEntrega status) {
        this.status = status;
    }
}
