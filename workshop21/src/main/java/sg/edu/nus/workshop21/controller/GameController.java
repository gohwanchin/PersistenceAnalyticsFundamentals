package sg.edu.nus.workshop21.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.workshop21.model.Comment;
import sg.edu.nus.workshop21.model.Game;
import sg.edu.nus.workshop21.repository.GameRepository;

@Controller
@RequestMapping("/game")
public class GameController {
    @Autowired
    private GameRepository gameRepo;

    @GetMapping("/{gid}")
    public String getGame(@PathVariable Integer gid, @RequestParam(defaultValue = "10") Integer limit,
            @RequestParam(defaultValue = "0") Integer offset,
            Model model) {
        Optional<Game> opt = gameRepo.getGameByGid(gid);
        List<Comment> list = new ArrayList<>();
        if (opt.isEmpty())
            return "error";
        list = gameRepo.getCommentsByGid(gid, limit, offset);
        model.addAttribute("game", opt.get());
        model.addAttribute("comments", list);
        model.addAttribute("offset", offset);
        return "game";
    }
}
