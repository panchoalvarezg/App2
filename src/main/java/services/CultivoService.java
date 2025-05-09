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

    // Método para eliminar un cultivo por nombre o categoría
    public boolean eliminarCultivoPorNombreOCategoria(String identificador) {
        return cultivos.removeIf(c -> c.getNombre().equalsIgnoreCase(identificador) || 
                                     c.getCategoria().equalsIgnoreCase(identificador));
    }

    // Método para buscar un cultivo por nombre o categoría
    public Cultivo buscarCultivoPorNombreOCategoria(String identificador) {
        return cultivos.stream()
                .filter(c -> c.getNombre().equalsIgnoreCase(identificador) || 
                             c.getCategoria().equalsIgnoreCase(identificador))
                .findFirst()
                .orElse(null);
    }

    // Método para editar un cultivo utilizando su nombre o categoría
    public boolean editarCultivo(String identificador, String nuevoNombre, String nuevaVariedad, String nuevoEstado, String nuevaCategoria) {
        Cultivo cultivo = buscarCultivoPorNombreOCategoria(identificador);
        if (cultivo == null) return false;

        if (nuevoNombre != null && !nuevoNombre.isEmpty()) cultivo.setNombre(nuevoNombre);
        if (nuevaVariedad != null && !nuevaVariedad.isEmpty()) cultivo.setVariedad(nuevaVariedad);
        if (nuevoEstado != null && !nuevoEstado.isEmpty()) cultivo.setEstado(nuevoEstado);
        if (nuevaCategoria != null && !nuevaCategoria.isEmpty()) cultivo.setCategoria(nuevaCategoria);

        return true;
    }

    // Método para actualizar la parcela de un cultivo utilizando su nombre o categoría
    public boolean actualizarParcela(String identificador, String nuevaParcela) {
        Cultivo cultivo = buscarCultivoPorNombreOCategoria(identificador);
        if (cultivo == null) return false;

        cultivo.setParcela(nuevaParcela);
        return true;
    }

    // Método para registrar actividades en un cultivo utilizando su nombre o categoría
    public boolean registrarActividad(String identificador, String tipo, String fecha) {
        Cultivo cultivo = buscarCultivoPorNombreOCategoria(identificador);
        if (cultivo == null) return false;

        Actividad nuevaActividad = new Actividad(tipo, java.time.LocalDate.parse(fecha));
        cultivo.getActividades().add(nuevaActividad);
        return true;
    }
}
