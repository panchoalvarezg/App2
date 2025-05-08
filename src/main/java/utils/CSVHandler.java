package utils;

import models.*;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;

public class CSVHandler {

    /**
     * Lee cultivos desde un archivo CSV y los convierte en una lista de objetos Cultivo.
     *
     * @param path Ruta al archivo CSV
     * @return Lista de objetos Cultivo
     */
    public List<Cultivo> leerCultivosDesdeCSV(String path) {
        List<Cultivo> cultivos = new ArrayList<>();
        try {
            List<String> lineas = Files.readAllLines(Paths.get(path));
            for (String linea : lineas) {
                if (!linea.startsWith("Cultivo")) continue;

                String[] partes = linea.split(",(?=\")");
                if (partes.length < 8) continue;

                String nombre = partes[1].replace("\"", "");
                String variedad = partes[2].replace("\"", "");
                double superficie = Double.parseDouble(partes[3]);
                String codigoParcela = partes[4].replace("\"", "");
                LocalDate fechaSiembra = LocalDate.parse(partes[5].replace("\"", ""));
                String estado = partes[6].replace("\"", "");

                Parcela parcela = new Parcela(codigoParcela, superficie, "");
                Cultivo cultivo = new Cultivo(nombre, variedad, superficie, parcela, fechaSiembra, estado);

                String actividadesRaw = partes[7].replace("[", "").replace("]", "").replace("\"", "");
                for (String act : actividadesRaw.split(",")) {
                    if (act.isBlank()) continue;
                    String[] datos = act.trim().split(":");
                    if (datos.length == 2) {
                        String tipo = datos[0];
                        LocalDate fecha = LocalDate.parse(datos[1]);
                        cultivo.getActividades().add(new Actividad(tipo, fecha));
                    }
                }

                cultivos.add(cultivo);
            }
        } catch (IOException e) {
            System.err.println("Error leyendo el archivo: " + e.getMessage());
        }
        return cultivos;
    }

    /**
     * Guarda una lista de cultivos en un archivo CSV.
     *
     * @param path     Ruta al archivo CSV
     * @param cultivos Lista de objetos Cultivo
     */
    public void guardarCultivosEnCSV(String path, List<Cultivo> cultivos) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(path))) {
            for (Cultivo c : cultivos) {
                String actividades = "[" + c.getActividades().stream()
                        .map(a -> a.getTipo() + ":" + a.getFecha())
                        .reduce((a, b) -> a + "," + b).orElse("") + "]";

                String linea = String.format(
                        "Cultivo,\"%s\",\"%s\",%.2f,\"%s\",\"%s\",\"%s\",\"%s\"",
                        c.getNombre(), c.getVariedad(), c.getArea(),
                        c.getParcela().getCodigo(), c.getFechaPlantacion(),
                        c.getEstado(), actividades
                );

                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error escribiendo el archivo: " + e.getMessage());
        }
    }
}
