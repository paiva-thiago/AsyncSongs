package br.ada.java.async;

import br.ada.java.musica.MusicaFiles;

import java.util.List;
import java.util.concurrent.*;

public class Esperaew{
    private static boolean processoConcluido = false;

    public static void main(String[] a) throws ExecutionException, InterruptedException {

        ExecutorService service = Executors.newSingleThreadExecutor();

        Future<?> salvandoArquivos = service.submit(() -> {
           processoConcluido = true;
            MusicaFiles.saveTop20();
            List<String> paises = List.of("PT","ES","DE","FR","GB","NL","BE","UA");
            for (String pais:paises) {
                MusicaFiles.saveTop200(pais);
            }
        });

        try {
            salvandoArquivos.get();
            if (processoConcluido) {
                System.out.println("Processo Concluído!");
            }
        } finally {
            service.shutdown();
        }
    }
}
