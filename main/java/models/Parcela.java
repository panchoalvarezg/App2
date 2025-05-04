// src/models/Parcela.java
package models;

public class Parcela {
    private String codigo;
    private double tamaño;
    private String ubicacion;

    public Parcela(String codigo, double tamaño, String ubicacion) {
        this.codigo = codigo;
        this.tamaño = tamaño;
        this.ubicacion = ubicacion;
    }

    public String getCodigo() { return codigo; }
    public double getTamaño() { return tamaño; }
    public String getUbicacion() { return ubicacion; }

    public void setTamaño(double tamaño) { this.tamaño = tamaño; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
}
