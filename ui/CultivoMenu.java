package ui;

import models.Cultivo;
import models.EstadoCultivo;
import models.Parcela;
import services.CultivoService;
import services.ParcelaService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class CultivoMenu {
    private static Scanner sc = new Scanner(System.in);

    /**
     * @param cs servicio de cultivos
     * @param ps servicio de parcelas (necesario para asignar parcela al crear)
     */
    public static void mostrar(CultivoService cs, ParcelaService ps) {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- Gestión de Cultivos ---");
            System.out.println("1. Listar todos los cultivos");
            System.out.println("2. Crear un nuevo cultivo");
            System.out.println("3. Editar un cultivo");
            System.out.println("4. Eliminar un cultivo");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Seleccione opción: ");
            String opt = sc.nextLine();
            switch (opt) {
                case "1":
                    List<Cultivo> lista = cs.getCultivos();
                    if (lista.isEmpty()) {
                        System.out.println("No hay cultivos registrados.");
                    } else {
                        lista.forEach(c -> System.out.println(c));
                    }
                    break;
                case "2":
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Variedad: ");
                    String variedad = sc.nextLine();
                    System.out.print("Superficie (ha): ");
                    double sup = Double.parseDouble(sc.nextLine());

                    // Mostrar parcelas disponibles
                    System.out.println("Parcelas disponibles:");
                    ps.getParcelas().forEach((cod, p) -> System.out.println(cod));
                    System.out.print("Código de parcela: ");
                    String codPar = sc.nextLine();
                    Parcela parcela = ps.getParcelas().get(codPar);
                    if (parcela == null) {
                        System.out.println("Parcela inválida.");
                        break;
                    }

                    System.out.print("Fecha de siembra (YYYY-MM-DD): ");
                    LocalDate fecha = LocalDate.parse(sc.nextLine());

                    System.out.print("Estado (ACTIVO, EN_RIESGO, COSECHADO): ");
                    EstadoCultivo est = EstadoCultivo.valueOf(sc.nextLine());

                    cs.agregarCultivo(nombre, variedad, sup, parcela, fecha, est);
                    System.out.println("Cultivo agregado.");
                    break;
                case "3":
                    // Editar
                    List<Cultivo> cults = cs.getCultivos();
                    if (cults.isEmpty()) {
                        System.out.println("No hay cultivos para editar.");
                        break;
                    }
                    for (int i = 0; i < cults.size(); i++) {
                        System.out.printf("%d) %s%n", i + 1, cults.get(i));
                    }
                    System.out.print("Seleccione número de cultivo a editar: ");
                    int idxE = Integer.parseInt(sc.nextLine()) - 1;
                    if (idxE < 0 || idxE >= cults.size()) {
                        System.out.println("Selección inválida.");
                        break;
                    }
                    Cultivo cE = cults.get(idxE);

                    System.out.print("Nuevo nombre (" + cE.getNombre() + "): ");
                    String nn = sc.nextLine();
                    System.out.print("Nueva variedad (" + cE.getVariedad() + "): ");
                    String nv = sc.nextLine();
                    System.out.print("Nueva superficie (" + cE.getSuperficie() + "): ");
                    double ns = Double.parseDouble(sc.nextLine());
                    System.out.print("Nueva fecha siembra (" + cE.getFechaSiembra() + "): ");
                    LocalDate nf = LocalDate.parse(sc.nextLine());
                    System.out.print("Nuevo estado (" + cE.getEstadoEnum() + "): ");
                    EstadoCultivo ne = EstadoCultivo.valueOf(sc.nextLine());

                    cs.editarCultivo(cE, 
                        nn.isBlank()  ? cE.getNombre()       : nn,
                        nv.isBlank()  ? cE.getVariedad()     : nv,
                        ns,
                        nf,
                        ne);
                    System.out.println("Cultivo editado.");
                    break;
                case "4":
                    // Eliminar
                    List<Cultivo> cults2 = cs.getCultivos();
                    for (int i = 0; i < cults2.size(); i++) {
                        System.out.printf("%d) %s%n", i + 1, cults2.get(i));
                    }
                    System.out.print("Seleccione número de cultivo a eliminar: ");
                    int idxD = Integer.parseInt(sc.nextLine()) - 1;
                    if (idxD < 0 || idxD >= cults2.size()) {
                        System.out.println("Selección inválida.");
                        break;
                    }
                    Cultivo cD = cults2.get(idxD);
                    boolean ok = cs.eliminarCultivo(cD);
                    System.out.println(ok ? "Cultivo eliminado." 
                                           : "No se puede eliminar: hay actividades pendientes.");
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
