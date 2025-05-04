
public class Cultivo extends ElementoAgricola {
    private String variedad;
    private double superficie;
    private Parcela parcela;
    private List<Actividad> actividades;

    public Cultivo(String nombre, String variedad, double superficie, Parcela parcela, LocalDate fechaSiembra, String estado) {
        super(nombre, fechaSiembra, estado);
        this.variedad = variedad;
        this.superficie = superficie;
        this.parcela = parcela;
        this.actividades = new ArrayList<>();
    }

    public String getVariedad() { return variedad; }
    public double getSuperficie() { return superficie; }
    public Parcela getParcela() { return parcela; }
    public List<Actividad> getActividades() { return actividades; }

    public void setVariedad(String variedad) { this.variedad = variedad; }
    public void setSuperficie(double superficie) { this.superficie = superficie; }
    public void setParcela(Parcela parcela) { this.parcela = parcela; }
}
