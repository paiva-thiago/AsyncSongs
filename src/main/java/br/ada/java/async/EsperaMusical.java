package br.ada.java.async;

import br.ada.java.musica.MusicaFiles;

import java.util.List;
import java.util.concurrent.*;

public class EsperaMusical {
    private static boolean processoConcluido = false;

    public static void main(String[] a) throws ExecutionException, InterruptedException {

        ExecutorService service = Executors.newSingleThreadExecutor();

        Future<?> enviandoMensagem = service.submit(() -> {
           List<String> paises = List.of("AR","BR","CL","CO","EC","PE","PY","UY","VE");
           for (String pais:paises) {
                MusicaFiles.saveTop200(pais);
           }
           processoConcluido = true;

        });

        try {
            enviandoMensagem.get(30, TimeUnit.SECONDS);
            if (processoConcluido) {
                System.out.println("Processo Concluído!");
            }
        } catch (TimeoutException e) {
            System.out.println("Tempo expirado");
        } finally {
            service.shutdown();
        }
    }
}
