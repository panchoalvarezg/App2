package utils;

import models.Cultivo;
import models.Actividad;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CSVHandler {
    public List<Cultivo> leerCultivosDesdeCSV(String path) {
        List<Cultivo> cultivos = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                if (partes.length < 8) continue;

                String nombre = partes[0].replace("\"", "");
                String variedad = partes[1].replace("\"", "");
                double area = Double.parseDouble(partes[2].replace("\"", ""));
                String parcela = partes[3].replace("\"", "");
                LocalDate fechaPlantacion = LocalDate.parse(partes[4].replace("\"", ""));
                String estado = partes[5].replace("\"", "");
                String categoria = partes[6].replace("\"", "");

                String actividadesRaw = partes[7].replace("[", "").replace("]", "").replace("\"", "");
                List<Actividad> actividades = new ArrayList<>();
                if (!actividadesRaw.isEmpty()) {
                    String[] actividadesPartes = actividadesRaw.split(",");
                    for (String actividadStr : actividadesPartes) {
                        String[] actividadDatos = actividadStr.split(":");
                        if (actividadDatos.length == 2) {
                            actividades.add(new Actividad(actividadDatos[0], LocalDate.parse(actividadDatos[1])));
                        }
                    }
                }

                cultivos.add(new Cultivo(nombre, variedad, area, parcela, fechaPlantacion, estado, categoria, actividades));
            }
        } catch (IOException e) {
            System.err.println("Error leyendo el archivo: " + e.getMessage());
        }
        return cultivos;
    }

    public void guardarCultivosEnCSV(String path, List<Cultivo> cultivos) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(path))) {
            for (Cultivo cultivo : cultivos) {
                String actividades = "[";
                actividades += String.join(",", cultivo.getActividades().stream()
                        .map(a -> "\"" + a.getTipo() + ":" + a.getFecha() + "\"")
                        .toArray(String[]::new));
                actividades += "]";

                writer.write("\"" + cultivo.getNombre() + "\",\"" +
                        cultivo.getVariedad() + "\"," +
                        cultivo.getArea() + ",\"" +
                        cultivo.getParcela() + "\",\"" +
                        cultivo.getFechaPlantacion() + "\",\"" +
                        cultivo.getEstado() + "\",\"" +
                        cultivo.getCategoria() + "\"," +
                        actividades);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar los cultivos en el archivo CSV: " + e.getMessage());
        }
    }
}
