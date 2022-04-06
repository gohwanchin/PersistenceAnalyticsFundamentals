package sg.edu.nus.day23.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.day23.model.Friend;
import sg.edu.nus.day23.repository.AddressBookRepository;

@Service
public class AddressBookService {

    @Autowired
    private AddressBookRepository addBookRepo;

    public Boolean checkEmail(String email) {
        return addBookRepo.checkExists(email);
    }

    public Boolean addFriend(Friend f) {
        try {
            return addBookRepo.addFriend(f);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<Friend> getFriends() {
        return addBookRepo.getFriends();
    }
}
