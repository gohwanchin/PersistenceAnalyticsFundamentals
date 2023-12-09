package vttp2022.paf.assessment.server.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import vttp2022.paf.assessment.server.models.Task;
import vttp2022.paf.assessment.server.services.TodoService;

@Controller
public class TasksController {
    @Autowired
    private TodoService todoSvc;

    @PostMapping("/task")
    public ModelAndView postTask(@RequestBody MultiValueMap<String, String> form) {
        ModelAndView mvc = new ModelAndView();
        String username = form.getFirst("username");
        Integer i = 0;
        List<Task> list = new ArrayList<>();
        while (form.getFirst("description-" + i) != null) {
            Task t = new Task();
            t.setDescription(form.getFirst("description-" + i));
            t.setPriority(Integer.parseInt(form.getFirst("priority-" + i)));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                t.setDueDate(format.parse(form.getFirst("dueDate-" + i)));
            } catch (Exception err) {
                System.out.println("date error");
                mvc.setViewName("error");
                mvc.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
                return mvc;
            }
            list.add(t);
            i++;
        }
        if (!todoSvc.upsertTask(username, list)) {
            mvc.setViewName("error");
            mvc.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return mvc;
        }
        mvc.addObject("taskCount", i.toString());
        mvc.setStatus(HttpStatus.OK);
        mvc.setViewName("result");
        return mvc;
    }
}
