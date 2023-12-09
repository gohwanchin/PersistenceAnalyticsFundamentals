package sg.edu.nus.mini_project.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import sg.edu.nus.mini_project.model.User;
import sg.edu.nus.mini_project.service.UserService;

@Controller
public class UserController {

    @Autowired
    UserService userSvc;

    @GetMapping
    public ModelAndView getLogin(HttpSession sess) {
        ModelAndView mvc = new ModelAndView();
        User u = new User();
        if (sess.getAttribute("username") != null && sess.getAttribute("user") != null) {
            System.out.println("Redirecting");
            return mvc = new ModelAndView("redirect:/t/searchPage");
        }
        mvc.addObject("user", u);
        mvc.setViewName("index");
        return mvc;
    }

    @GetMapping("/signup")
    public ModelAndView getSignup() {
        ModelAndView mvc = new ModelAndView();
        User u = new User();
        mvc.addObject("user", u);
        mvc.setViewName("signup");
        return mvc;
    }

    @PostMapping("/authenticate")
    public ModelAndView postLogin(@ModelAttribute User u, HttpSession sess) {
        ModelAndView mvc = new ModelAndView();
        if (userSvc.login(u)) {
            sess.setAttribute("username", u.getUsername());
            sess.setAttribute("user", u);
            System.out.println("Redirecting");
            return mvc = new ModelAndView("redirect:/t/searchPage");
        }
        User user = new User();
        mvc.addObject("user", user);
        mvc.addObject("msg", "Wrong username or password");
        mvc.setViewName("index");
        return mvc;
    }

    @PostMapping("/signup")
    public ModelAndView postSignup(@ModelAttribute User u, HttpSession sess) {
        ModelAndView mvc = new ModelAndView();
        if (userSvc.addUser(u)) {
            sess.setAttribute("username", u.getUsername());
            sess.setAttribute("user", u);
            System.out.println("Redirecting");
            return mvc = new ModelAndView("redirect:/t/searchPage");
        }
        User user = new User();
        mvc.addObject("user", user);
        mvc.addObject("msg", "User unable to be created. Username already exists");
        mvc.setViewName("signup");
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
