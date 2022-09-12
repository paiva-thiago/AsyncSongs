package br.ada.java.musica;

import br.ada.java.musica.config.ApiProperties;
import com.google.gson.JsonArray;
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

public class MusicaService {
    private static String API_KEY= ApiProperties.getKey();
    private static String API_HOST= ApiProperties.getHost();
    private static String SPOTIFY_URI = "https://spotify81.p.rapidapi.com";

    private static HttpRequest top200(String pais){
        return HttpRequest.newBuilder()
                .uri(URI.create(SPOTIFY_URI.concat("/top_200_tracks?country=")+pais))
                .header("X-RapidAPI-Key", API_KEY)
                .header("X-RapidAPI-Host", API_HOST)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
    }
    private static HttpRequest top20(){
        return HttpRequest.newBuilder()
                .uri(URI.create(SPOTIFY_URI.concat("/top_20_by_monthly_listeners")))
                .header("X-RapidAPI-Key", API_KEY)
                .header("X-RapidAPI-Host", API_HOST)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
    }

    public static List<String> getTop20() {
        List<String> ret = new ArrayList<>();
        String response = getContent(top20());
        JsonElement jsonElement = JsonParser.parseString(response);
        JsonArray arr = jsonElement.getAsJsonArray();
        arr.forEach(el->{
            JsonObject o = el.getAsJsonObject();
            String rank = o.get("rank").getAsString();
            String artist = o.get("artist").getAsString();
            String s = rank.concat(" - ").concat(artist);
            ret.add(s);
        });
        return ret;
    }
    public static List<String> getTop200(String pais){
        List<String> ret = new ArrayList<>();
        String response = getContent(top200(pais));
        JsonElement jsonElement = JsonParser.parseString(response);
        JsonArray arr = jsonElement.getAsJsonArray();
        arr.forEach(el->{
            JsonObject o = el.getAsJsonObject();
            String rank = o.get("chartEntryData").getAsJsonObject().get("currentRank").getAsString();
            JsonObject t = o.get("trackMetadata").getAsJsonObject();
            JsonArray  arts = t.get("artists").getAsJsonArray();
            String artists = "";
            for(JsonElement artist: arts) {
                artists = artists.concat(artist.getAsJsonObject().get("name").getAsString()).concat("/");
            }
            artists = artists.substring(0,artists.length()-1);
            String track = t.get("trackName").getAsString();
            String s = rank.concat(" - ").concat(artists).concat(" - ").concat(track);
            ret.add(s);
        });
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
