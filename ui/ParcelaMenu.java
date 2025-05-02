package ui;

import models.Cultivo;
import models.Parcela;
import services.ParcelaService;
import services.CultivoService;

import java.util.List;
import java.util.Scanner;

public class ParcelaMenu {
    private static Scanner sc = new Scanner(System.in);

    /**
     * @param ps servicio de parcelas
     * @param cs servicio de cultivos (para asignar)
     */
    public static void mostrar(ParcelaService ps, CultivoService cs) {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- Gestión de Parcelas ---");
            System.out.println("1. Listar parcelas");
            System.out.println("2. Agregar una parcela");
            System.out.println("3. Editar una parcela");
            System.out.println("4. Eliminar una parcela");
            System.out.println("5. Asignar cultivo a parcela");
            System.out.println("6. Volver al Menú Principal");
            System.out.print("Seleccione opción: ");
            String opt = sc.nextLine();
            switch (opt) {
                case "1":
                    ps.getParcelas().values()
                      .forEach(p -> System.out.println(p));
                    break;
                case "2":
                    System.out.print("Código: ");
                    String cod = sc.nextLine();
                    System.out.print("Área (ha): ");
                    double area = Double.parseDouble(sc.nextLine());
                    System.out.print("Ubicación: ");
                    String ubi = sc.nextLine();
                    boolean cre = ps.agregarParcela(cod, area, ubi);
                    System.out.println(cre ? "Parcela añadida." 
                                           : "Ya existe esa parcela.");
                    break;
                case "3":
                    System.out.print("Código de parcela a editar: ");
                    String ec = sc.nextLine();
                    System.out.print("Nueva área: ");
                    double na = Double.parseDouble(sc.nextLine());
                    System.out.print("Nueva ubicación: ");
                    String nu = sc.nextLine();
                    boolean ed = ps.editarParcela(ec, na, nu);
                    System.out.println(ed ? "Parcela editada." 
                                         : "No existe esa parcela.");
                    break;
                case "4":
                    System.out.print("Código de parcela a eliminar: ");
                    String dc = sc.nextLine();
                    boolean del = ps.eliminarParcela(dc);
                    System.out.println(del ? "Parcela eliminada." 
                                          : "No se puede eliminar (tiene cultivos o no existe).");
                    break;
                case "5":
                    // Mostrar parcelas
                    System.out.println("Parcelas:");
                    ps.getParcelas().forEach((k,v)->System.out.println(k));
                    System.out.print("Código de parcela: ");
                    String pc = sc.nextLine();
                    // Mostrar cultivos
                    List<Cultivo> cults = cs.getCultivos();
                    for (int i = 0; i < cults.size(); i++) {
                        System.out.printf("%d) %s%n", i+1, cults.get(i));
                    }
                    System.out.print("Número de cultivo a asignar: ");
                    int ci = Integer.parseInt(sc.nextLine()) - 1;
                    if (ci < 0 || ci >= cults.size()) {
                        System.out.println("Selección inválida.");
                        break;
                    }
                    boolean asg = ps.asignarCultivo(pc, cults.get(ci));
                    System.out.println(asg ? "Cultivo asignado." 
                                          : "Error al asignar (parcela inexistente).");
                    break;
                case "6":
                    volver = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}