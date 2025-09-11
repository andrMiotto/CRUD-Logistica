package org.example.model;

import java.time.LocalDate;

public class EntregaCompleta {
    private int entregaId;
    private int pedidoId;
    private String clienteNome;
    private String motoristaNome;
    private StatusEntrega status;
    private LocalDate dataSaida;
    private LocalDate dataEntrega;


    public EntregaCompleta(int entregaId, int pedidoId, String clienteNome, String motoristaNome, StatusEntrega status, LocalDate dataSaida, LocalDate dataEntrega) {
        this.entregaId = entregaId;
        this.pedidoId = pedidoId;
        this.clienteNome = clienteNome;
        this.motoristaNome = motoristaNome;
        this.status = status;
        this.dataSaida = dataSaida;
        this.dataEntrega = dataEntrega;
    }

    public int getEntregaId() {
        return entregaId;
    }

    public void setEntregaId(int entregaId) {
        this.entregaId = entregaId;
    }

    public int getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }

    public String getMotoristaNome() {
        return motoristaNome;
    }

    public void setMotoristaNome(String motoristaNome) {
        this.motoristaNome = motoristaNome;
    }

    public StatusEntrega getStatus() {
        return status;
    }

    public void setStatus(StatusEntrega status) {
        this.status = status;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }
}
