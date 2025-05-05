// src/utils/CSVHandler.java
package utils;

import models.*;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;

public class CSVHandler {
    public List<Cultivo> leerCultivosDesdeCSV(String path) {
        List<Cultivo> cultivos = new ArrayList<>();
        try {
            List<String> lineas = Files.readAllLines(Paths.get(path));
            for (String linea : lineas) {
                String[] partes = linea.split(",(?=\"|")");
                if (!partes[0].equals("Cultivo")) continue;
                String nombre = partes[1].replace("\"", "");
                String variedad = partes[2].replace("\"", "");
                double superficie = Double.parseDouble(partes[3]);
                String codigoParcela = partes[4].replace("\"", "");
                LocalDate fechaSiembra = LocalDate.parse(partes[5].replace("\"", ""));
                String estado = partes[6].replace("\"", "");

                Parcela parcela = new Parcela(codigoParcela, 0.0, ""); // Simplicidad
                Cultivo cultivo = new Cultivo(nombre, variedad, superficie, parcela, fechaSiembra, estado);

                // Actividades (sólo representación simple)
                String actividadesRaw = partes[7].replace("[", "").replace("]", "").replace("\"", "");
                for (String act : actividadesRaw.split(",")) {
                    if (act.isBlank()) continue;
                    String[] datos = act.trim().split(":");
                    cultivo.getActividades().add(new Actividad(datos[0], LocalDate.parse(datos[1])));
                }
                cultivos.add(cultivo);
            }
        } catch (IOException e) {
            System.out.println("Error leyendo el archivo: " + e.getMessage());
        }
        return cultivos;
    }

    public void guardarCultivosEnCSV(String path, List<Cultivo> cultivos) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(path))) {
            for (Cultivo c : cultivos) {
                String actividades = "[" + c.getActividades().stream()
                        .map(a -> a.getTipo() + ":" + a.getFecha())
                        .reduce((a, b) -> a + "," + b).orElse("") + "]";
                String linea = String.format("Cultivo,\"%s\",\"%s\",%.2f,\"%s\",\"%s\",\"%s\",\"%s\"",
                        c.getNombre(), c.getVariedad(), c.getSuperficie(),
                        c.getParcela().getCodigo(), c.getFechaSiembra(), c.getEstado(), actividades);
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error escribiendo el archivo: " + e.getMessage());
        }
    }
}
