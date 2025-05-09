package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cultivo {
    private String nombre;
    private String variedad;
    private double area;
    private String parcela;
    private LocalDate fechaPlantacion;
    private String estado;
    private List<Actividad> actividades; // Ahora actividades es una lista directamente

    // Constructor actualizado para aceptar una lista de actividades
    public Cultivo(String nombre, String variedad, double area, String parcela, LocalDate fechaPlantacion, String estado, List<Actividad> actividades) {
        this.nombre = nombre;
        this.variedad = variedad;
        this.area = area;
        this.parcela = parcela;
        this.fechaPlantacion = fechaPlantacion;
        this.estado = estado;
        this.actividades = actividades;
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getVariedad() { return variedad; }
    public double getArea() { return area; }
    public String getParcela() { return parcela; }
    public LocalDate getFechaPlantacion() { return fechaPlantacion; }
    public String getEstado() { return estado; }
    public List<Actividad> getActividades() { return actividades; }

    // Setters (opcional, si son necesarios)
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setVariedad(String variedad) { this.variedad = variedad; }
    public void setArea(double area) { this.area = area; }
    public void setParcela(String parcela) { this.parcela = parcela; }
    public void setFechaPlantacion(LocalDate fechaPlantacion) { this.fechaPlantacion = fechaPlantacion; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setActividades(List<Actividad> actividades) { this.actividades = actividades; }
}
