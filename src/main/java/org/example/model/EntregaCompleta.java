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
    private Integer totalEntregas;
    private Double volumeTotal;
    private String estado;
    private String cidade;
    private int total_entregas_atrasadas;

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public int getTotal_entregas_atrasadas() {
        return total_entregas_atrasadas;
    }

    public void setTotal_entregas_atrasadas(int total_entregas_atrasadas) {
        this.total_entregas_atrasadas = total_entregas_atrasadas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getVolumeTotal() {
        return volumeTotal;
    }

    public void setVolumeTotal(Double volumeTotal) {
        this.volumeTotal = volumeTotal;
    }

    public EntregaCompleta() {
        this.entregaId = entregaId;
        this.pedidoId = pedidoId;
        this.clienteNome = clienteNome;
        this.motoristaNome = motoristaNome;
        this.status = status;
        this.dataSaida = dataSaida;
        this.dataEntrega = dataEntrega;
        this.totalEntregas = totalEntregas;
    }

    public Integer getTotalEntregas() {
        return totalEntregas;
    }

    public void setTotalEntregas(Integer totalEntregas) {
        this.totalEntregas = totalEntregas;
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
