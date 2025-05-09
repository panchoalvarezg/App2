package services;

import models.Cultivo;
import models.Actividad;

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

    // **Nuevo Método** para buscar un cultivo por nombre
    public Cultivo buscarCultivo(String nombre) {
        return cultivos.stream()
                .filter(c -> c.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

    // **Nuevo Método** para editar un cultivo
    public boolean editarCultivo(String nombre, String nuevoNombre, String nuevaVariedad, String nuevoEstado) {
        Cultivo cultivo = buscarCultivo(nombre);
        if (cultivo == null) return false;

        if (nuevoNombre != null && !nuevoNombre.isEmpty()) cultivo.setNombre(nuevoNombre);
        if (nuevaVariedad != null && !nuevaVariedad.isEmpty()) cultivo.setVariedad(nuevaVariedad);
        if (nuevoEstado != null && !nuevoEstado.isEmpty()) cultivo.setEstado(nuevoEstado);

        return true;
    }

    // **Nuevo Método** para agregar o actualizar parcela de un cultivo
    public boolean actualizarParcela(String nombre, String nuevaParcela) {
        Cultivo cultivo = buscarCultivo(nombre);
        if (cultivo == null) return false;

        cultivo.setParcela(nuevaParcela);
        return true;
    }

    // **Nuevo Método** para registrar actividades en un cultivo
    public boolean registrarActividad(String nombre, String tipo, String fecha) {
        Cultivo cultivo = buscarCultivo(nombre);
        if (cultivo == null) return false;

        Actividad nuevaActividad = new Actividad(tipo, java.time.LocalDate.parse(fecha));
        cultivo.getActividades().add(nuevaActividad);
        return true;
    }
}
