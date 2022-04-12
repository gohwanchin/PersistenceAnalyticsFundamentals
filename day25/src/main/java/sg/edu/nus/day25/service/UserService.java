package sg.edu.nus.day25.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.day25.model.User;
import sg.edu.nus.day25.repository.AccountsRepository;

@Service
public class UserService {

    @Autowired
    AccountsRepository accRepo;

    public Boolean login(User u) {
        return accRepo.userLogin(u);
    }
}
