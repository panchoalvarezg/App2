package utils;

import models.Actividad;
import models.Cultivo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class CSVHandler {

    /**
     * Lee cultivos desde un archivo CSV y los convierte en una lista de objetos Cultivo.
     *
     * @param path Ruta al archivo CSV.
     * @return Lista de objetos Cultivo.
     */
    public List<Cultivo> leerCultivosDesdeCSV(String path) {
        List<Cultivo> cultivos = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                // Ignorar líneas vacías
                if (linea.trim().isEmpty()) continue;

                // Dividir los datos respetando las comillas
                String[] partes = linea.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                if (partes.length < 7) continue; // Validar que haya suficientes columnas

                // Extraer datos del CSV
                String nombre = partes[1].replace("\"", "").trim();
                String variedad = partes[2].replace("\"", "").trim();
                double area = Double.parseDouble(partes[3].replace("\"", "").trim());
                String parcela = partes[4].replace("\"", "").trim();
                String fechaPlantacion = partes[5].replace("\"", "").trim();
                String estado = partes[6].replace("\"", "").trim();

                // Procesar la lista de actividades
                String actividadesRaw = partes[7].replace("[", "").replace("]", "").replace("\"", "").trim();
                List<Actividad> actividades = new ArrayList<>();
                if (!actividadesRaw.isEmpty()) {
                    String[] actividadesPartes = actividadesRaw.split(",");
                    for (String actividadStr : actividadesPartes) {
                        String[] actividadDatos = actividadStr.split(":");
                        if (actividadDatos.length == 2) {
                            String tipo = actividadDatos[0].trim();
                            String fecha = actividadDatos[1].trim();
                            actividades.add(new Actividad(tipo, LocalDate.parse(fecha)));
                        }
                    }
                }

                // Crear el objeto Cultivo y añadirlo a la lista
                Cultivo cultivo = new Cultivo(nombre, variedad, area, parcela, fechaPlantacion, estado, actividades);
                cultivos.add(cultivo);
            }
        } catch (IOException e) {
            System.err.println("Error leyendo el archivo: " + e.getMessage());
        } catch (NumberFormatException | DateTimeParseException e) {
            System.err.println("Error en el formato de los datos: " + e.getMessage());
        }
        return cultivos;
    }

    /**
     * Guarda una lista de cultivos en un archivo CSV.
     *
     * @param path     Ruta al archivo CSV.
     * @param cultivos Lista de objetos Cultivo.
     */
    public void guardarCultivosEnCSV(String path, List<Cultivo> cultivos) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(path))) {
            for (Cultivo c : cultivos) {
                // Convertir la lista de actividades a texto
                String actividades = "[" + c.getActividades().stream()
                        .map(a -> "\"" + a.getTipo() + ":" + a.getFecha() + "\"")
                        .reduce((a, b) -> a + "," + b).orElse("") + "]";

                // Formatear la línea para el archivo CSV
                String linea = String.format(
                        "Cultivo,\"%s\",\"%s\",%.2f,\"%s\",\"%s\",\"%s\",%s",
                        c.getNombre(), c.getVariedad(), c.getArea(),
                        c.getParcela(), c.getFechaPlantacion(),
                        c.getEstado(), actividades
                );

                // Escribir la línea en el archivo
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error escribiendo el archivo: " + e.getMessage());
        }
    }
}
