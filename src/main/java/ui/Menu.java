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
                        System.out.println("- " + c.getNombre() + " | " + c.getVariedad() + " | Estado: " + c.getEstado()));
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
                String[] values = line.split(","); // Suponiendo que los valores están separados por comas
                if (values.length == 3) { // Validar que la línea tenga exactamente 3 columnas
                    String nombre = values[0];
                    String variedad = values[1];
                    String estado = values[2];
                    cultivos.add(new Cultivo(nombre, variedad, estado));
                } else {
                    System.err.println("Línea con formato incorrecto: " + line);
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
