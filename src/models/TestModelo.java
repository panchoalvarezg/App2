import models.Cultivo;
import java.util.Scanner;

public class TestModelo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Bucle para seguir creando cultivos
        while (true) {
            System.out.print("Ingresa el nombre del cultivo (o 'salir' para terminar): ");
            String nombreCultivo = scanner.nextLine();

            if (nombreCultivo.equalsIgnoreCase("salir")) {
                break;
            }

            Cultivo cultivo = new Cultivo(nombreCultivo);
            System.out.println("Cultivo creado: " + cultivo);
        }

        scanner.close();
    }
}
