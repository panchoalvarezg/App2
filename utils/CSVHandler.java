package utils;

import com.opencsv.*;
import com.opencsv.exceptions.CsvException;
import models.*;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class CSVHandler {

    /**
     * Lee cultivos de un CSV usando OpenCSV.
     */
    public static List<Cultivo> leerCultivos(String filePath) 
            throws IOException, CsvException {
        List<Cultivo> cultivos = new ArrayList<>();
        Map<String, Parcela> parcelasMap = new HashMap<>();

        try (CSVReader reader = new CSVReaderBuilder(new FileReader(filePath))
                                   .withCSVParser(new CSVParserBuilder()
                                       .withSeparator(',')
                                       .withQuoteChar('\"')
                                       .build())
                                   .build()) {
            List<String[]> rows = reader.readAll();
            for (String[] parts : rows) {
                // parts[0] == "Cultivo"
                String nombre     = parts[1];
                String variedad   = parts[2];
                double superficie = Double.parseDouble(parts[3]);
                String codPar     = parts[4];
                LocalDate fecha   = LocalDate.parse(parts[5]);
                EstadoCultivo est = EstadoCultivo.valueOf(parts[6]);

                // La lista de actividades viene en parts[7] como:
                // ["RIEGO:2023-03-10","COSECHA:2023-06-15"]
                String rawActs = parts[7];
                rawActs = rawActs.substring(1, rawActs.length() - 1); // quita [ ]

                List<Actividad> actividades = new ArrayList<>();
                if (!rawActs.isEmpty()) {
                    // separar por "," garantizando que no rompa las comillas
                    // rawActs.split("\",\"") funciona porque OpenCSV ya quitó las comillas exteriores
                    String[] arr = rawActs.split("\",\"");
                    for (String s : arr) {
                        String[] pa = s.split(":", 2);
                        Actividad.Tipo tipo = Actividad.Tipo.valueOf(pa[0]);
                        LocalDate f = LocalDate.parse(pa[1]);
                        actividades.add(new Actividad(tipo, f));
                    }
                }

                Parcela parcela = parcelasMap.computeIfAbsent(
                    codPar, k -> new Parcela(k, 0.0, "")
                );

                Cultivo c = new Cultivo(nombre, variedad, superficie, parcela, fecha, est);
                actividades.forEach(c::addActividad);
                parcela.addCultivo(c);
                cultivos.add(c);
            }
        }

        return cultivos;
    }

    /**
     * Escribe la lista de cultivos en un CSV usando OpenCSV.
     */
    public static void guardarCultivos(List<Cultivo> cultivos, String filePath) 
            throws IOException {
        try (CSVWriter writer = new CSVWriterBuilder(new FileWriter(filePath))
                                  .withSeparator(',')
                                  .withQuoteChar('\"')
                                  .build()) {
            for (Cultivo c : cultivos) {
                // reconstruir el campo de actividades
                StringBuilder actsBuilder = new StringBuilder("[");
                List<Actividad> acts = c.getActividades();
                for (int i = 0; i < acts.size(); i++) {
                    Actividad a = acts.get(i);
                    actsBuilder.append(a.getTipo())
                               .append(":")
                               .append(a.getFecha());
                    if (i < acts.size() - 1) actsBuilder.append("\",\"");
                }
                actsBuilder.append("]");

                String[] line = new String[] {
                    "Cultivo",
                    c.getNombre(),
                    c.getVariedad(),
                    String.valueOf(c.getSuperficie()),
                    c.getParcela().getCodigo(),
                    c.getFechaSiembra().toString(),
                    c.getEstadoEnum().name(),
                    actsBuilder.toString()
                };

                writer.writeNext(line, false);
            }
        }
    }
}


