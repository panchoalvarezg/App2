package models;

public class Cultivo {
    private String nombre;
    private String variedad;
    private double area;
    private String parcela;
    private String fechaPlantacion;
    private String estado;
    private String actividades;

    public Cultivo(String nombre, String variedad, double area, String parcela, String fechaPlantacion, String estado, String actividades) {
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
    public String getFechaPlantacion() { return fechaPlantacion; }
    public String getEstado() { return estado; }
    public String getActividades() { return actividades; }
}
