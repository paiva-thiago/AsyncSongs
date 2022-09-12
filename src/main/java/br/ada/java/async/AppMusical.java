package br.ada.java.async;

import br.ada.java.musica.MusicaFiles;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class AppMusical {

    public  static void main(String[] args) {
        Runnable salvarTop20 = () -> {
            MusicaFiles.saveTop20();
        };
        Runnable salvarTop200 = () -> {
            List<String> paises = List.of("AR","BR","CL","CO","EC","PE","PY","UY","VE");
            for (String pais:paises) {
                MusicaFiles.saveTop200(pais);
            }
        };
        System.out.println("VAMOS COMEÇAR!");
        ExecutorService service = Executors.newSingleThreadExecutor();
        try {
            service.execute(salvarTop20);
            service.execute(salvarTop200);
        }finally {
            service.shutdown();
        }
        System.out.println("TÉRMINO DA NOSSA EXECUÇÃO");
    }
}
