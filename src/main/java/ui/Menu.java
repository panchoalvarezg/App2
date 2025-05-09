package ui;

import models.Cultivo;
import models.Actividad;
import services.CultivoService;
import utils.CSVHandler;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;

public class Menu {
    private final CultivoService cultivoService;
    private final CSVHandler csvHandler;
    private final String csvPath = "src/main/resources/cultivos.csv";
    private final Scanner scanner = new Scanner(System.in);

    public Menu(CultivoService cultivoService, CSVHandler csvHandler) {
        this.cultivoService = cultivoService;
        this.csvHandler = csvHandler;
    }

    public void mostrarMenu() {
        boolean salir = false;
        while (!salir) {
            System.out.println("\nMenú Principal:");
            System.out.println("1. Listar cultivos");
            System.out.println("2. Crear un cultivo");
            System.out.println("3. Editar un cultivo");
            System.out.println("4. Eliminar un cultivo");
            System.out.println("5. Agregar/Editar parcelas");
            System.out.println("6. Registrar actividades");
            System.out.println("7. Buscar cultivos/parcelas");
            System.out.println("8. Guardar y salir");
            System.out.print("Seleccione una opción: ");

            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1": listarCultivos(); break;
                case "2": agregarCultivo(); break;
                case "3": editarCultivo(); break;
                case "4": eliminarCultivo(); break;
                case "5": gestionarParcelas(); break;
                case "6": registrarActividades(); break;
                case "7": buscarCultivosParcelas(); break;
                case "8": guardarYSalir(); salir = true; break;
                default: System.out.println("Opción inválida");
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

        Cultivo cultivo = new Cultivo(nombre, variedad, area, parcela, fechaPlantacion, estado, new ArrayList<>());
        cultivoService.agregarCultivo(cultivo);
    }

    private void editarCultivo() {
        System.out.print("Ingrese el nombre del cultivo a editar: ");
        String nombre = scanner.nextLine();

        Cultivo cultivo = cultivoService.buscarCultivo(nombre);
        if (cultivo == null) {
            System.out.println("No se encontró un cultivo con ese nombre.");
            return;
        }

        System.out.print("Ingrese el nuevo nombre del cultivo (o presione enter para mantener el actual): ");
        String nuevoNombre = scanner.nextLine();
        if (!nuevoNombre.isEmpty()) cultivo.setNombre(nuevoNombre);

        System.out.print("Ingrese la nueva variedad (o presione enter para mantener la actual): ");
        String nuevaVariedad = scanner.nextLine();
        if (!nuevaVariedad.isEmpty()) cultivo.setVariedad(nuevaVariedad);

        System.out.println("Cultivo editado exitosamente.");
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

    private void gestionarParcelas() {
        System.out.print("Ingrese el nombre del cultivo para gestionar parcelas: ");
        String nombre = scanner.nextLine();

        Cultivo cultivo = cultivoService.buscarCultivo(nombre);
        if (cultivo == null) {
            System.out.println("No se encontró un cultivo con ese nombre.");
            return;
        }

        System.out.print("Ingrese el nuevo código de parcela: ");
        String nuevaParcela = scanner.nextLine();
        cultivo.setParcela(nuevaParcela);

        System.out.println("Parcela actualizada exitosamente.");
    }

    private void registrarActividades() {
        System.out.print("Ingrese el nombre del cultivo para registrar actividades: ");
        String nombre = scanner.nextLine();

        Cultivo cultivo = cultivoService.buscarCultivo(nombre);
        if (cultivo == null) {
            System.out.println("No se encontró un cultivo con ese nombre.");
            return;
        }

        System.out.print("Ingrese el tipo de actividad: ");
        String tipo = scanner.nextLine();

        System.out.print("Ingrese la fecha de la actividad (YYYY-MM-DD): ");
        LocalDate fecha = LocalDate.parse(scanner.nextLine());

        cultivo.getActividades().add(new Actividad(tipo, fecha));
        System.out.println("Actividad registrada exitosamente.");
    }

    private void buscarCultivosParcelas() {
        System.out.print("Ingrese el nombre o código para buscar: ");
        String termino = scanner.nextLine();

        cultivoService.getCultivos().stream()
            .filter(c -> c.getNombre().equalsIgnoreCase(termino) || c.getParcela().equalsIgnoreCase(termino))
            .forEach(c -> System.out.println("- " + c.getNombre() + " | " + c.getVariedad() + " | Parcela: " + c.getParcela()));
    }

    private void guardarYSalir() {
        csvHandler.guardarCultivosEnCSV(csvPath, cultivoService.getCultivos());
        System.out.println("Cambios guardados exitosamente. ¡Adiós!");
    }

    public static void main(String[] args) {
        CSVHandler csvHandler = new CSVHandler();
        CultivoService cultivoService = new CultivoService(csvHandler.leerCultivosDesdeCSV("src/main/resources/cultivos.csv"));

        Menu menu = new Menu(cultivoService, csvHandler);
        menu.mostrarMenu();
    }
}
