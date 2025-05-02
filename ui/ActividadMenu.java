package ui;

import models.Cultivo;
import models.Actividad;
import models.Actividad.Tipo;
import services.ActividadService;
import services.CultivoService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ActividadMenu {
    private static Scanner sc = new Scanner(System.in);

    public static void mostrar(CultivoService cs) {
        List<Cultivo> cultivos = cs.getCultivos();
        if (cultivos.isEmpty()) {
            System.out.println("No hay cultivos para gestionar actividades.");
            return;
        }

        // Selección de cultivo
        System.out.println("\n--- Seleccione un cultivo ---");
        for (int i = 0; i < cultivos.size(); i++) {
            System.out.printf("%d) %s%n", i + 1, cultivos.get(i));
        }
        System.out.print("Número: ");
        int idx = Integer.parseInt(sc.nextLine()) - 1;
        if (idx < 0 || idx >= cultivos.size()) {
            System.out.println("Selección inválida.");
            return;
        }
        Cultivo sel = cultivos.get(idx);
        ActividadService as = new ActividadService(sel);

        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- Actividades de " + sel.getNombre() + " ---");
            System.out.println("1. Registrar actividad");
            System.out.println("2. Listar actividades");
            System.out.println("3. Eliminar actividad");
            System.out.println("4. Marcar completada");
            System.out.println("5. Volver");
            System.out.print("Opción: ");
            String opt = sc.nextLine();
            switch (opt) {
                case "1":
                    System.out.println("Tipos: " + List.of(Tipo.values()));
                    System.out.print("Tipo: ");
                    Tipo t = Tipo.valueOf(sc.nextLine());
                    System.out.print("Fecha (YYYY-MM-DD): ");
                    LocalDate f = LocalDate.parse(sc.nextLine());
                    as.registrarActividad(new Actividad(t, f));
                    System.out.println("Actividad registrada.");
                    break;
                case "2":
                    List<Actividad> acts = as.listarActividades();
                    if (acts.isEmpty()) {
                        System.out.println("No hay actividades.");
                    } else {
                        acts.forEach(a -> System.out.println(a.getTipo() + " @ " 
                                                            + a.getFecha() 
                                                            + " [" + a.getEstado() + "]"));
                    }
                    break;
                case "3":
                    List<Actividad> acts2 = as.listarActividades();
                    for (int i = 0; i < acts2.size(); i++) {
                        System.out.printf("%d) %s%n", i+1, acts2.get(i).getTipo() + " @ " + acts2.get(i).getFecha());
                    }
                    System.out.print("Número a eliminar: ");
                    int di = Integer.parseInt(sc.nextLine()) - 1;
                    if (di < 0 || di >= acts2.size()) {
                        System.out.println("Selección inválida.");
                        break;
                    }
                    as.eliminarActividad(acts2.get(di));
                    System.out.println("Actividad eliminada.");
                    break;
                case "4":
                    List<Actividad> acts3 = as.listarActividades();
                    for (int i = 0; i < acts3.size(); i++) {
                        System.out.printf("%d) %s%n", i+1, acts3.get(i).getTipo() + " @ " + acts3.get(i).getFecha());
                    }
                    System.out.print("Número a marcar completada: ");
                    int ci2 = Integer.parseInt(sc.nextLine()) - 1;
                    if (ci2 < 0 || ci2 >= acts3.size()) {
                        System.out.println("Selección inválida.");
                        break;
                    }
                    as.marcarCompletada(acts3.get(ci2));
                    System.out.println("Marcada como completada.");
                    break;
                case "5":
                    volver = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}
