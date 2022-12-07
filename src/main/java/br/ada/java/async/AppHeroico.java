package br.ada.java.async;

import br.ada.java.musica.HeroFiles;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppHeroico {

    public static void main(String[] args) {
        List<String> heroesFromDC = List.of("Superman","Batman","Robin","Cyborg","Flash","WonderWoman","GreenLantern","Raven");
        List<String> heroesFromMarvel = List.of("IronMan","CaptainAmerica","Spiderman","Thor","Hulk");

        Runnable salvarDC = () -> HeroFiles.saveHeroes(heroesFromDC,"DC");
        Runnable salvarMarvel= () ->HeroFiles.saveHeroes(heroesFromMarvel,"Marvel");

        System.out.println("VAMOS COMEÇAR!");
        ExecutorService service = Executors.newSingleThreadExecutor();
        try {
            service.execute(salvarDC);
            service.execute(salvarMarvel);
        }finally {
            service.shutdown();
        }
        System.out.println("TÉRMINO DA NOSSA EXECUÇÃO");
    }
}
