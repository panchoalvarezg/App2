// src/services/CultivoService.java
package services;

import models.Cultivo;
import java.util.ArrayList;
import java.util.List;

public class CultivoService {
    private List<Cultivo> cultivos;

    public CultivoService(List<Cultivo> cultivos) {
        this.cultivos = cultivos != null ? cultivos : new ArrayList<>();
    }

    public List<Cultivo> getCultivos() {
        return cultivos;
    }

    public void agregarCultivo(Cultivo cultivo) {
        cultivos.add(cultivo);
    }

    public void eliminarCultivo(Cultivo cultivo) {
        cultivos.remove(cultivo);
    }
}
