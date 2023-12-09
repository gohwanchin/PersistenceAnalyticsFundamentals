package sg.edu.nus.day22.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.day22.service.GiphyService;

@Controller
public class GiphyController {
    @Autowired
    GiphyService giphySvc;

    @GetMapping("/search")
    public String getSearch(@RequestParam String phrase, @RequestParam String limit,
            @RequestParam String rating, Model model) {
        List<String> gifUrls = giphySvc.searchGif(phrase, limit, rating);
        model.addAttribute("urls", gifUrls);
        model.addAttribute("phrase", phrase.toUpperCase());
        return "result";
    }
}
