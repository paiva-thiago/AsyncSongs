package br.ada.java.musica;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class MusicaFiles {
    public static void saveTop200(String pais){
        System.out.println("Obtendo Lista Top 200: Sigla do país:".concat(pais));
        saveFile(MusicaService.getTop200(pais),"top200_".concat(pais));
    }
    public static void saveTop20(){
        System.out.println("Obtendo Lista Top 20...");
        saveFile(MusicaService.getTop20(),"top20");
    }
    private static void saveFile(List<String> list,String filename){
        try (PrintWriter out = new PrintWriter("src/main/resources/output/"+filename+".txt")) {
            for(String text:list) {
                out.println(text);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
