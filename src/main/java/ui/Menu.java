// src/ui/Menu.java
package ui;

import services.CultivoService;
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
        // Inicialización del servicio CultivoService
        CultivoService cultivoService = new CultivoService();

        // Crear el menú y mostrarlo
        Menu menu = new Menu(cultivoService);
        menu.mostrarMenu();
    }
}
