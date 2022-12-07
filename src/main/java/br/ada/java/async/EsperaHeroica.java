package br.ada.java.async;

import br.ada.java.musica.HeroFiles;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class EsperaHeroica {
    private static boolean processoConcluido = false;

    public static void main(String[] a) throws ExecutionException, InterruptedException {
        List<String> heroesFromDC = List.of("Superman","Batman","Robin","Cyborg","Flash","WonderWoman","GreenLantern","Raven");
        List<String> heroesFromMarvel = List.of("IronMan","CaptainAmerica","Spiderman","Thor","Hulk");

        ExecutorService service = Executors.newSingleThreadExecutor();

        Future<?> salvandoArquivos = service.submit(() -> {
            HeroFiles.saveHeroes(heroesFromDC,"DC2");
            HeroFiles.saveHeroes(heroesFromMarvel,"Marvel2");
            processoConcluido = true;
        });


        try {
            salvandoArquivos.get();
            if(processoConcluido) System.out.println("Processo Concluído!");
        } finally {
            service.shutdown();
        }
    }
}
