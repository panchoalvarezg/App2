
/**
 * Representa un cultivo con un nombre.
 */
public class Cultivo {
    private String nombre;

    /**
     * Constructor para crear un cultivo con su nombre.
     *
     * @param nombre Nombre del cultivo (ej. "Tomate").
     */
    public Cultivo(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Cultivo{" +
                "nombre='" + nombre + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cultivo)) return false;
        Cultivo cultivo = (Cultivo) o;
        return nombre.equalsIgnoreCase(cultivo.nombre);
    }

    @Override
    public int hashCode() {
        return nombre.toLowerCase().hashCode();
    }
}
