package sg.edu.nus.day24.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import sg.edu.nus.day24.model.PO;
import sg.edu.nus.day24.service.PoService;

@Controller
public class PoController {

    @Autowired
    PoService poSvc;

    @GetMapping
    public String getPO(Model model) {
        PO po = new PO();
        model.addAttribute("po", po);
        return "index";
    }

    @PostMapping("/purchase_order")
    public String postPO(@ModelAttribute PO po, Model model) {
        try {
            poSvc.addPO(po);
        } catch (DataAccessException err) {
            model.addAttribute("msg", err.getMessage());
            return "error";
        }
        PO newPo = new PO();
        model.addAttribute("po", newPo);
        return "index";
    }
}
