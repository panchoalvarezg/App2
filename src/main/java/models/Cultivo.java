package models;

import java.time.LocalDate;
import java.util.List;

public class Cultivo {
    private String nombre;
    private String variedad;
    private double area;
    private String parcela;
    private LocalDate fechaPlantacion;
    private String estado;
    private String categoria; // NUEVO: atributo para la categoría
    private List<Actividad> actividades;

    // Constructor actualizado para incluir la categoría
    public Cultivo(String nombre, String variedad, double area, String parcela, LocalDate fechaPlantacion, String estado, String categoria, List<Actividad> actividades) {
        this.nombre = nombre;
        this.variedad = variedad;
        this.area = area;
        this.parcela = parcela;
        this.fechaPlantacion = fechaPlantacion;
        this.estado = estado;
        this.categoria = categoria; // Asignar la categoría
        this.actividades = actividades;
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getVariedad() { return variedad; }
    public double getArea() { return area; }
    public String getParcela() { return parcela; }
    public LocalDate getFechaPlantacion() { return fechaPlantacion; }
    public String getEstado() { return estado; }
    public String getCategoria() { return categoria; } // NUEVO getter para categoría
    public List<Actividad> getActividades() { return actividades; }

    // Setters
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setVariedad(String variedad) { this.variedad = variedad; }
    public void setArea(double area) { this.area = area; }
    public void setParcela(String parcela) { this.parcela = parcela; }
    public void setFechaPlantacion(LocalDate fechaPlantacion) { this.fechaPlantacion = fechaPlantacion; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setCategoria(String categoria) { this.categoria = categoria; } // NUEVO setter para categoría
    public void setActividades(List<Actividad> actividades) { this.actividades = actividades; }
}
