package sg.edu.nus.day25.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import sg.edu.nus.day25.model.User;

@Controller
@RequestMapping("/protected/{view}")
public class ProtectedController {
    @RequestMapping
    public ModelAndView view(@PathVariable String view, HttpSession sess) {
        System.out.println("Protected controller");
        String username = (String) sess.getAttribute("username");
        User user = (User) sess.getAttribute("user");
        ModelAndView mvc = new ModelAndView();
        mvc.setViewName(view);
        mvc.addObject("username", username);
        mvc.addObject("user", user);
        mvc.setStatus(HttpStatus.OK);
        return mvc;
    }
}
