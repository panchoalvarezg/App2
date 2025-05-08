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
                // Saltar encabezado o líneas que no cumplan con el formato esperado
                if (!linea.startsWith("Cultivo")) continue;

                // Dividir la línea en partes usando una expresión regular que respeta las comillas
                String[] partes = linea.split(",(?=\")");

                // Validar que la línea tenga al menos la cantidad esperada de partes
                if (partes.length < 8) continue;

                // Parsear los valores
                String nombre = partes[1].replace("\"", "");
                String variedad = partes[2].replace("\"", "");
                double superficie = Double.parseDouble(partes[3]);
                String codigoParcela = partes[4].replace("\"", "");
                LocalDate fechaSiembra = LocalDate.parse(partes[5].replace("\"", ""));
                String estado = partes[6].replace("\"", "");

                // Crear el objeto Parcela
                Parcela parcela = new Parcela(codigoParcela, superficie, ""); // Superficie y descripción opcionales

                // Crear el objeto Cultivo
                Cultivo cultivo = new Cultivo(nombre, variedad, superficie, parcela, fechaSiembra, estado);

                // Parsear actividades
                String actividadesRaw = partes[7]
                        .replace("[", "")
                        .replace("]", "")
                        .replace("\"", "");
                for (String act : actividadesRaw.split(",")) {
                    if (act.isBlank()) continue;
                    String[] datos = act.trim().split(":");
                    if (datos.length == 2) { // Validar que tiene exactamente tipo y fecha
                        String tipo = datos[0];
                        LocalDate fecha = LocalDate.parse(datos[1]);
                        cultivo.getActividades().add(new Actividad(tipo, fecha));
                    }
                }

                // Agregar cultivo a la lista
                cultivos.add(cultivo);
            }
        } catch (IOException e) {
            System.err.println("Error leyendo el archivo: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error procesando el archivo: " + e.getMessage());
        }
        return cultivos;
    }

    /**
     * Guarda una lista de cultivos en un archivo CSV.
     *
     * @param path Ruta al archivo CSV
     * @param cultivos Lista de objetos Cultivo
     */
    public void guardarCultivosEnCSV(String path, List<Cultivo> cultivos) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(path))) {
            // Escribir los cultivos en el archivo CSV
            for (Cultivo c : cultivos) {
                // Convertir actividades a texto
                String actividades = "[" + c.getActividades().stream()
                        .map(a -> a.getTipo() + ":" + a.getFecha())
                        .reduce((a, b) -> a + "," + b).orElse("") + "]";

                // Formatear la línea del CSV
                String linea = String.format(
                        "Cultivo,\"%s\",\"%s\",%.2f,\"%s\",\"%s\",\"%s\",\"%s\"",
                        c.getNombre(),
                        c.getVariedad(),
                        c.getArea(),
                        c.getParcela().getCodigo(),
                        c.getFechaPlantacion(),
                        c.getEstado(),
                        actividades
                );

                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error escribiendo el archivo: " + e.getMessage());
        }
    }
}
