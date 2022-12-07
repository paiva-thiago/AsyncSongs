package br.ada.java.musica;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class HeroFiles {
    public static void saveHeroes(List<String> heroes, String origin) {
        saveFile(HeroGenderService.getHeroesGender(heroes),origin);
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