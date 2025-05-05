package app;

import services.CultivoService;
import ui.Menu;
import utils.CSVHandler;

import java.util.List;

public class App2 {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java App2 cultivos.csv");
            return;
        }

        String archivoCSV = args[0];
        CSVHandler csvHandler = new CSVHandler();
        List<models.Cultivo> cultivos = csvHandler.leerCultivosDesdeCSV(archivoCSV);

        CultivoService cultivoService = new CultivoService(cultivos);
        Menu menu = new Menu(cultivoService);
        menu.mostrarMenu();

        csvHandler.guardarCultivosEnCSV(archivoCSV, cultivoService.getCultivos());
        System.out.println("Datos guardados exitosamente en " + archivoCSV);
    }
}
