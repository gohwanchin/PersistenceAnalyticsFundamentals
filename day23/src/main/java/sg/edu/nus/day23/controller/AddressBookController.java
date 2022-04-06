package sg.edu.nus.day23.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import sg.edu.nus.day23.model.Friend;
import sg.edu.nus.day23.service.AddressBookService;

@Controller
public class AddressBookController {

    @Autowired
    private AddressBookService addBookSvc;

    @GetMapping
    public String getIndex(Model model) {
        List<Friend> list = addBookSvc.getFriends();
        Friend f = new Friend();
        model.addAttribute("list", list);
        model.addAttribute("friend", f);
        model.addAttribute("msg", "Add a friend!");
        return "index";
    }

    @PostMapping("/add")
    public String postFriend(@ModelAttribute Friend f, Model model) {
        String email = f.getEmail();
        if (addBookSvc.checkEmail(email)) {
            List<Friend> list = addBookSvc.getFriends();
            model.addAttribute("list", list);
            model.addAttribute("msg", "Friend already exists!!");
            return "index";
        }
        if (addBookSvc.addFriend(f)) {
            List<Friend> list = addBookSvc.getFriends();
            model.addAttribute("list", list);
            model.addAttribute("msg", "Added successfully!!");
            return "index";
        }
        List<Friend> list = addBookSvc.getFriends();
        model.addAttribute("list", list);
        model.addAttribute("msg", "Failed to add friend!!");
        return "index";
    }
}
