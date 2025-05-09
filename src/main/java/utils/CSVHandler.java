package utils;

import models.Actividad;
import models.Cultivo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
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
                if (partes.length < 8) continue; // Ahora tenemos 8 columnas con la categoría

                // Extraer datos del CSV
                String nombre = partes[0].replace("\"", "").trim();
                String variedad = partes[1].replace("\"", "").trim();
                double area = Double.parseDouble(partes[2].replace("\"", "").trim());
                String parcela = partes[3].replace("\"", "").trim();
                LocalDate fechaPlantacion = LocalDate.parse(partes[4].replace("\"", "").trim());
                String estado = partes[5].replace("\"", "").trim();
                String categoria = partes[6].replace("\"", "").trim(); // NUEVO: leer la categoría

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
                Cultivo cultivo = new Cultivo(nombre, variedad, area, parcela, fechaPlantacion, estado, categoria, actividades);
                cultivos.add(cultivo);
            }
        } catch (IOException e) {
            System.err.println("Error leyendo el archivo: " + e.getMessage());
        } catch (Exception e) {
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
            for (Cultivo cultivo : cultivos) {
                // Convertir la lista de actividades a texto
                String actividades = "[";
                actividades += String.join(",", cultivo.getActividades().stream()
                        .map(a -> a.getTipo() + ":" + a.getFecha().toString())
                        .toArray(String[]::new));
                actividades += "]";

                // Escribir cada línea en el archivo
                writer.write("\"" + cultivo.getNombre() + "\",\"" +
                        cultivo.getVariedad() + "\",\"" +
                        cultivo.getArea() + "\",\"" +
                        cultivo.getParcela() + "\",\"" +
                        cultivo.getFechaPlantacion() + "\",\"" +
                        cultivo.getEstado() + "\",\"" +
                        cultivo.getCategoria() + "\",\"" + // NUEVO: escribir la categoría
                        actividades + "\"");
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar los cultivos en el archivo CSV: " + e.getMessage());
        }
    }
}
