package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cultivo {
    private String nombre;
    private String variedad;
    private double area;
    private Parcela parcela;
    private LocalDate fechaPlantacion;
    private String estado;
    private List<Actividad> actividades;

    // Constructor original
    public Cultivo(String nombre, String variedad, double area, String parcela, String fechaPlantacion, String estado, String actividades) {
        this.nombre = nombre;
        this.variedad = variedad;
        this.area = area;
        this.parcela = new Parcela(parcela, 0.0, ""); // Convertir String a Parcela
        this.fechaPlantacion = LocalDate.parse(fechaPlantacion); // Convertir String a LocalDate
        this.estado = estado;
        this.actividades = new ArrayList<>(); // Inicializar actividades como lista vacía
    }

    // Nuevo constructor que utiliza tipos LocalDate y Parcela
    public Cultivo(String nombre, String variedad, double area, Parcela parcela, LocalDate fechaPlantacion, String estado) {
        this.nombre = nombre;
        this.variedad = variedad;
        this.area = area;
        this.parcela = parcela;
        this.fechaPlantacion = fechaPlantacion;
        this.estado = estado;
        this.actividades = new ArrayList<>(); // Inicializar actividades como lista vacía
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getVariedad() { return variedad; }
    public double getArea() { return area; }
    public Parcela getParcela() { return parcela; }
    public LocalDate getFechaPlantacion() { return fechaPlantacion; }
    public String getEstado() { return estado; }
    public List<Actividad> getActividades() { return actividades; }
}
