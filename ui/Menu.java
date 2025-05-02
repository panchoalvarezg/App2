package ui;

import services.CultivoService;
import services.ParcelaService;
import java.util.Scanner;

public class Menu {
    // Scanner compartido para todos los menús
    private static Scanner sc = new Scanner(System.in);

    public static void mostrarMenuPrincipal(CultivoService cs, ParcelaService ps) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Gestión de Cultivos");
            System.out.println("2. Gestión de Parcelas");
            System.out.println("3. Gestión de Actividades");
            System.out.println("4. Búsqueda/Reporte");
            System.out.println("5. Salir");
            System.out.print("Seleccione opción: ");
            String o = sc.nextLine();    // <--- aquí usamos sc, no ui.Menu.sc

            switch (o) {
                case "1":
                    CultivoMenu.mostrar(cs, ps);
                    break;
                case "2":
                    ParcelaMenu.mostrar(ps, cs);
                    break;
                case "3":
                    ActividadMenu.mostrar(cs);
                    break;
                case "4":
                    ReporteMenu.mostrar(cs);
                    break;
                case "5":
                    System.out.println("Guardando y saliendo...");
                    exit = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}

