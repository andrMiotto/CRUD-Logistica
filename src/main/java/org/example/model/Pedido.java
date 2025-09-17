package org.example.model;

import java.time.LocalDate;

public class Pedido {

    private int id;
    private int clienteId; 
    private LocalDate dataPedido;
    private double volumeM3;
    private double pesoKg;
    private StatusPedido status;

    public Pedido() {
        this.clienteId = 0;
        this.dataPedido = LocalDate.now();
        this.volumeM3 = 0;
        this.pesoKg = 0;
        this.status = StatusPedido.PENDENTE;
    }



    public Pedido(int clienteId, LocalDate dataPedido, double volumeM3, double pesoKg) {
        this.clienteId = clienteId; 
        this.dataPedido = dataPedido;
        this.volumeM3 = volumeM3;
        this.pesoKg = pesoKg;
        this.status = StatusPedido.PENDENTE;
    }

    public Pedido(int id, int clienteId, LocalDate dataPedido, double volumeM3, double pesoKg, StatusPedido status) {
        this.id = id;
        this.clienteId = clienteId;
        this.dataPedido = dataPedido;
        this.volumeM3 = volumeM3;
        this.pesoKg = pesoKg;
        this.status = status;
    }

    public Pedido(int id, LocalDate dataPedido) {
        this.id = id;
        this.dataPedido = dataPedido;
    }

    public Pedido(int id, LocalDate dataPedido, double volumeM3, double pesoKg, String status, String clienteNome) {

    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public int getClienteId() {

        return clienteId;
    }

    public void setClienteId(int clienteId) {

        this.clienteId = clienteId;
    }

    public LocalDate getDataPedido() {

        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public double getVolumeM3() {

        return volumeM3;
    }

    public void setVolumeM3(double volumeM3) {

        this.volumeM3 = volumeM3;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(double pesoKg) {
        this.pesoKg = pesoKg;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", clienteId=" + clienteId +
                ", dataPedido=" + dataPedido +
                ", volumeM3=" + volumeM3 +
                ", pesoKg=" + pesoKg +
                ", status=" + status +
                '}';
    }
}
