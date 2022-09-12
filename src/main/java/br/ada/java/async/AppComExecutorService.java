package br.ada.java.async;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppComExecutorService {

    public static void main(String[] args) {
        Runnable imprimirCatalogo = () -> {
            dorme(500);
            System.out.println("Imprimindo catalogo");

        };
        Runnable imprimirRegistros = () -> {
            for (int i = 0; i < 4; i++) {
                dorme(2000);
                System.out.println("Imprimindo registro: " + i);

            }

        };

        ExecutorService service = Executors.newSingleThreadExecutor();
        try {
            System.out.println("inicio");
            service.execute(imprimirCatalogo);
            service.execute(imprimirRegistros);
            service.execute(imprimirCatalogo);
            System.out.println("fim");
        } finally {
            service.shutdown();
        }
    }
    private static void dorme(int msec){
        try {
            Thread.sleep(msec);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
