package sg.edu.nus.mini_project.service;

import java.io.StringReader;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import sg.edu.nus.mini_project.model.Media;
import sg.edu.nus.mini_project.model.SearchPage;
import sg.edu.nus.mini_project.model.Title;

@Service
public class SearchService {
    private static final String API_URL = "http://www.omdbapi.com/";

    @Value("${omdb.api}")
    String apiKey;

    public SearchPage searchMedia(String query, Integer page) {
        query = query.replace(" ", "-");
        String url = UriComponentsBuilder.fromUriString(API_URL)
                .queryParam("apikey", apiKey)
                .queryParam("s", query)
                .queryParam("page", page)
                .toUriString();
        System.out.println(url);
        RequestEntity<Void> req = RequestEntity.get(url).accept(MediaType.APPLICATION_JSON).build();
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.exchange(req, String.class);
        JsonObject o = Json.createReader(new StringReader(resp.getBody())).readObject();
        SearchPage searchPage = new SearchPage(page,query);
        if (o.getString("Response").equals("False")) {
            searchPage.setTotalResultsandPages(0);
            return searchPage;
        }
        searchPage.setTotalResultsandPages(Integer.parseInt(o.getString("totalResults")));
        List<Media> list = o.getJsonArray("Search")
                .stream()
                .map(m -> (JsonObject) m)
                .map(m -> Media.create(m))
                .toList();
        searchPage.setResults(list);
        return searchPage;
    }

    public Optional<Title> getTitle(String id) {
        String url = UriComponentsBuilder.fromUriString(API_URL)
                .queryParam("apikey", apiKey)
                .queryParam("i", id)
                .toUriString();
        RequestEntity<Void> req = RequestEntity.get(url).accept(MediaType.APPLICATION_JSON).build();
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.exchange(req, String.class);
        JsonObject o = Json.createReader(new StringReader(resp.getBody())).readObject();
        if (o.getString("Response").equals("False"))
            return Optional.empty();
        return Optional.of(Title.create(o));
    }

    public Media getMedia(String id) {
        String url = UriComponentsBuilder.fromUriString(API_URL)
                .queryParam("apikey", apiKey)
                .queryParam("i", id)
                .toUriString();
        RequestEntity<Void> req = RequestEntity.get(url).accept(MediaType.APPLICATION_JSON).build();
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.exchange(req, String.class);
        JsonObject o = Json.createReader(new StringReader(resp.getBody())).readObject();
        if (o.getString("Response").equals("False"))
            return null;
        return Media.create(o);
    }
}
