package br.ada.java.musica;

import br.ada.java.musica.config.ApiProperties;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class HeroGenderService {
    private static String GENDERIZE_URL = "https://api.genderize.io/";

    private static HttpRequest gender(String hero){
        return HttpRequest.newBuilder()
                .uri(URI.create(GENDERIZE_URL.concat("/?name=")+hero))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
    }

    public static List<String> getHeroesGender(List<String> heroes){
        List<String> ret = new ArrayList<>();
        for(String hero:heroes) {
            String response = getContent(gender(hero));
            JsonElement jsonElement = JsonParser.parseString(response);
            JsonObject o = jsonElement.getAsJsonObject();
            String gender = o.get("gender").getAsString();
            String heroAndGender = hero.concat("-").concat(gender);
            ret.add(heroAndGender);
        }
        return ret;
    }
    private static String getContent(HttpRequest request){
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response.body();
    }

}
