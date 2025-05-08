package ui;

import services.CultivoService;
import models.Cultivo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final CultivoService cultivoService;
    private final Scanner scanner = new Scanner(System.in);

    public Menu(CultivoService cultivoService) {
        this.cultivoService = cultivoService;
    }

    public void mostrarMenu() {
        boolean salir = false;
        while (!salir) {
            System.out.println("\nMenú Principal:");
            System.out.println("1. Listar cultivos");
            System.out.println("2. Salir");
            System.out.print("Seleccione una opción: ");

            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    cultivoService.getCultivos().forEach(c ->
                        System.out.println("- " + c.getNombre() + " | " + c.getVariedad() + " | Estado: " + c.getEstado() + " | Parcela: " + c.getParcela()));
                    break;
                case "2":
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    public static void main(String[] args) {
        List<Cultivo> cultivos = new ArrayList<>();

        // Ruta del archivo CSV
        String filePath = "src/main/resources/cultivos.csv";

        // Leer el archivo CSV
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Cultivo")) { // Saltar encabezado si lo tiene
                    String[] values = line.split(",");
                    
                    // Parsear los valores, eliminando comillas dobles
                    String nombre = values[1].replace("\"", "");
                    String variedad = values[2].replace("\"", "");
                    double area = Double.parseDouble(values[3]);
                    String parcela = values[4].replace("\"", "");
                    String fechaPlantacion = values[5].replace("\"", "");
                    String estado = values[6].replace("\"", "");
                    String actividades = values[7].replace("\"", "");

                    // Crear y agregar un objeto Cultivo
                    cultivos.add(new Cultivo(nombre, variedad, area, parcela, fechaPlantacion, estado, actividades));
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
        }

        // Instanciar el servicio CultivoService con los cultivos cargados
        CultivoService cultivoService = new CultivoService(cultivos);

        // Crear el menú y mostrarlo
        Menu menu = new Menu(cultivoService);
        menu.mostrarMenu();
    }
}
