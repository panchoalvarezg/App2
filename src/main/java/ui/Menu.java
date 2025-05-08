package ui;

import models.Cultivo;
import services.CultivoService;

import java.time.LocalDate;
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
            System.out.println("2. Agregar cultivo");
            System.out.println("3. Eliminar cultivo");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    listarCultivos();
                    break;
                case "2":
                    agregarCultivo();
                    break;
                case "3":
                    eliminarCultivo();
                    break;
                case "4":
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void listarCultivos() {
        System.out.println("\nLista de cultivos:");
        cultivoService.getCultivos().forEach(c ->
            System.out.println("- " + c.getNombre() + " | " + c.getVariedad() + " | Estado: " + c.getEstado() + " | Parcela: " + c.getParcela())
        );
    }

    private void agregarCultivo() {
        System.out.print("Ingrese el nombre del cultivo: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese la variedad del cultivo: ");
        String variedad = scanner.nextLine();

        System.out.print("Ingrese el área del cultivo (en hectáreas): ");
        double area = Double.parseDouble(scanner.nextLine());

        System.out.print("Ingrese el código de la parcela: ");
        String parcela = scanner.nextLine();

        System.out.print("Ingrese la fecha de plantación (YYYY-MM-DD): ");
        LocalDate fechaPlantacion = LocalDate.parse(scanner.nextLine());

        System.out.print("Ingrese el estado del cultivo: ");
        String estado = scanner.nextLine();

        Cultivo cultivo = new Cultivo(nombre, variedad, area, parcela, fechaPlantacion.toString(), estado, "");
        cultivoService.agregarCultivo(cultivo);
    }

    private void eliminarCultivo() {
        System.out.print("Ingrese el nombre del cultivo a eliminar: ");
        String nombre = scanner.nextLine();

        if (cultivoService.eliminarCultivo(nombre)) {
            System.out.println("Cultivo eliminado exitosamente.");
        } else {
            System.out.println("No se encontró un cultivo con ese nombre.");
        }
    }

    public static void main(String[] args) {
        // Inicialización del servicio CultivoService con una lista inicial vacía
        CultivoService cultivoService = new CultivoService(new ArrayList<>());

        // Crear el menú y mostrarlo
        Menu menu = new Menu(cultivoService);
        menu.mostrarMenu();
    }
}
