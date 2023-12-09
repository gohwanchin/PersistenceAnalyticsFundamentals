package sg.edu.nus.day22.service;

import java.util.List;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class GiphyService {
    @Value("${giphy.key}")
    String apiKey;

    private static final String API_URL = "https://api.giphy.com/v1/gifs/search";

    // Overloaded methods
    public List<String> searchGif(String phrase) {
        return searchGif(phrase, "10", "PG");
    }

    public List<String> searchGif(String phrase, String limit) {
        return searchGif(phrase, limit, "PG");
    }

    public List<String> searchGif(String phrase, String limit, String rating) {
        String url = UriComponentsBuilder.fromUriString(API_URL)
                .queryParam("api_key", apiKey)
                .queryParam("q", phrase)
                .queryParam("limit", limit)
                .queryParam("rating", rating)
                .toUriString();
        RequestEntity<Void> req = RequestEntity.get(url).build();
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.exchange(req, String.class);
        DocumentContext jsonContext = JsonPath.parse(resp.getBody());
        List<String> gifUrls = jsonContext.read("$.data.*.images.fixed_width.url");
        return gifUrls;
    }
}
