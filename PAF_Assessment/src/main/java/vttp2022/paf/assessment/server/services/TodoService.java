package vttp2022.paf.assessment.server.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vttp2022.paf.assessment.server.models.Task;
import vttp2022.paf.assessment.server.models.User;
import vttp2022.paf.assessment.server.repositories.TaskRepository;

@Service
public class TodoService {
    @Autowired private TaskRepository taskRepo;
    @Autowired private UserService userSvc;

    @Transactional
    public boolean upsertTask(String username, List<Task> list) {
        Optional<User> opt = userSvc.findUserByUsername(username);
        User user = new User();
        if (opt.isEmpty()){
            user.setUsername(username);
            user.setUserId(userSvc.insertUser(user));
        }else{
            user = opt.get();
        }
        System.out.println(user.toString());
        for(Task t:list){
            t.setUserId(user.getUserId());
            if(!taskRepo.insertTask(t))
                throw new IllegalArgumentException("Cannot add task");
        }
        return true;
    }
}
