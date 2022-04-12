package sg.edu.nus.day25.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import sg.edu.nus.day25.model.User;
import sg.edu.nus.day25.service.UserService;

@Controller
public class UserController {

    @Autowired
    UserService userSvc;

    @GetMapping
    public ModelAndView getLogin() {
        ModelAndView mvc = new ModelAndView();
        User u = new User();
        mvc.addObject("user", u);
        mvc.setViewName("index");
        return mvc;
    }

    @PostMapping("/authenticate")
    public ModelAndView postLogin(@ModelAttribute User u, HttpSession sess) {
        ModelAndView mvc = new ModelAndView();
        if (userSvc.login(u)) {
            // mvc.setStatus(HttpStatus.OK);
            // mvc.addObject("user", u);
            // mvc.setViewName("welcome");
            sess.setAttribute("username", u.getUsername());
            sess.setAttribute("user", u);
            System.out.println("Redirecting");
            return mvc = new ModelAndView("redirect:/protected/welcome");
        }
        mvc.setStatus(HttpStatus.UNAUTHORIZED);
        mvc.setViewName("error");
        return mvc;
    }

    @GetMapping("/logout")
    public ModelAndView getLogout(HttpSession sess) {
        sess.invalidate();
        ModelAndView mvc = new ModelAndView();
        User u = new User();
        mvc.addObject("user", u);
        mvc.setViewName("index");
        return mvc;
    }
}
