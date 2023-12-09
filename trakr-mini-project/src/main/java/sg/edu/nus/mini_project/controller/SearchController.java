package sg.edu.nus.mini_project.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sg.edu.nus.mini_project.model.Media;
import sg.edu.nus.mini_project.model.SearchPage;
import sg.edu.nus.mini_project.model.Title;
import sg.edu.nus.mini_project.model.User;
import sg.edu.nus.mini_project.service.SearchService;
import sg.edu.nus.mini_project.service.UserService;

@Controller
@RequestMapping("/t")
public class SearchController {

    @Autowired
    SearchService searchSvc;

    @Autowired
    UserService userSvc;

    @GetMapping("/searchPage")
    public ModelAndView getSearch(HttpSession sess) {
        ModelAndView mvc = new ModelAndView();
        User u = (User) sess.getAttribute("user");
        mvc.addObject("user", u);
        mvc.setViewName("search");
        return mvc;
    }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam String query, @RequestParam(defaultValue = "1") Integer page) {
        ModelAndView mvc = new ModelAndView();
        SearchPage searchPage = searchSvc.searchMedia(query, page);
        mvc.addObject("searchPage", searchPage);
        mvc.setViewName("results");
        return mvc;
    }

    @GetMapping("/watchlist")
    public ModelAndView getWatchlist(HttpSession sess) {
        ModelAndView mvc = new ModelAndView();
        User u = (User) sess.getAttribute("user");
        List<Media> watchlist = userSvc.getWatchlist(u);
        mvc.addObject("watchlist", watchlist);
        mvc.setViewName("watchlist");
        return mvc;
    }

    @GetMapping("/title/{id}")
    public ModelAndView getTitle(@PathVariable String id, HttpSession sess) {
        ModelAndView mvc = new ModelAndView();
        Optional<Title> opt = searchSvc.getTitle(id);
        if (opt.isEmpty()) {
            mvc.addObject("msg", "Title not found");
            mvc.setViewName("error");
            return mvc;
        }
        User u = (User) sess.getAttribute("user");
        Boolean added = userSvc.checkTitleExists(u, id);
        mvc.addObject("title", opt.get());
        mvc.addObject("added", added);
        mvc.setViewName("title");
        return mvc;
    }

    @PostMapping("/title/{id}/add")
    public ModelAndView addTitleToWatchlist(@PathVariable String id, HttpSession sess) {
        User u = (User) sess.getAttribute("user");
        userSvc.addTitleToWatchlist(u, id);
        return new ModelAndView("redirect:/t/title/" + id);
    }

    @PostMapping("/title/{id}/remove")
    public ModelAndView removeTitleFromWatchlist(@PathVariable String id, HttpSession sess) {
        User u = (User) sess.getAttribute("user");
        userSvc.removeTitleFromWatchlist(u, id);
        return new ModelAndView("redirect:/t/title/" + id);
    }

}
