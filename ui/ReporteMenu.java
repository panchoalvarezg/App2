package ui;

import models.Cultivo;
import models.EstadoCultivo;
import services.CultivoService;

import java.util.List;
import java.util.Scanner;

public class ReporteMenu {
    private static Scanner sc = new Scanner(System.in);

    public static void mostrar(CultivoService cs) {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- Búsqueda y Reporte ---");
            System.out.println("1. Buscar cultivos por nombre o variedad");
            System.out.println("2. Reporte de cultivos por estado");
            System.out.println("3. Volver al Menú Principal");
            System.out.print("Opción: ");
            String opt = sc.nextLine();
            switch (opt) {
                case "1":
                    System.out.print("Nombre o variedad a buscar: ");
                    String term = sc.nextLine();
                    List<Cultivo> res = cs.buscarPorNombreOVariedad(term);
                    if (res.isEmpty()) System.out.println("No se encontraron resultados.");
                    else res.forEach(c -> System.out.println(c));
                    break;
                case "2":
                    System.out.print("Estado (ACTIVO, EN_RIESGO, COSECHADO): ");
                    try {
                        EstadoCultivo e = EstadoCultivo.valueOf(sc.nextLine());
                        List<Cultivo> rpt = cs.reportePorEstado(e);
                        if (rpt.isEmpty()) System.out.println("No hay cultivos en ese estado.");
                        else rpt.forEach(c -> System.out.println(c));
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Estado inválido.");
                    }
                    break;
                case "3":
                    volver = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}

