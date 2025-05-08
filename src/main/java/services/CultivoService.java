package services;

import models.Cultivo;

import java.util.ArrayList;
import java.util.List;

public class CultivoService {
    private final List<Cultivo> cultivos;

    public CultivoService(List<Cultivo> cultivos) {
        this.cultivos = cultivos != null ? cultivos : new ArrayList<>();
    }

    // Método para obtener la lista de cultivos
    public List<Cultivo> getCultivos() {
        return cultivos;
    }

    // Método para agregar un cultivo
    public void agregarCultivo(Cultivo cultivo) {
        cultivos.add(cultivo);
        System.out.println("Cultivo agregado exitosamente.");
    }

    // Método para eliminar un cultivo por nombre
    public boolean eliminarCultivo(String nombre) {
        return cultivos.removeIf(c -> c.getNombre().equalsIgnoreCase(nombre));
    }
}
