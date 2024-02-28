package com.example.gestioninstituto.models;

import java.time.LocalDate;
import java.util.Date;

public class Dispositivo {
    private String id;
    private LocalDate fechaCompra;
    private TipoDispositivo tipo;
    private String marca;
    private String modelo;

    public Dispositivo(String identificador, LocalDate fechaCompra, TipoDispositivo tipo, String marca, String modelo) {
        this.id = identificador;
        this.fechaCompra = fechaCompra;
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = modelo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public TipoDispositivo getTipo() {
        return tipo;
    }

    public void setTipo(TipoDispositivo tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return "Dispositivo{" +
                "id='" + id + '\'' +
                ", fechaCompra=" + fechaCompra +
                ", tipo=" + tipo +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                '}';
    }
}
