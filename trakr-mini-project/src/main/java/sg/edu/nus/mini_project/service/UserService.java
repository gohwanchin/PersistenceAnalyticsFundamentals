package sg.edu.nus.mini_project.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.mini_project.model.Media;
import sg.edu.nus.mini_project.model.User;
import sg.edu.nus.mini_project.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    SearchService searchSvc;

    public Boolean login(User u) {
        return userRepo.userLogin(u);
    }

    public Boolean addUser(User u) {
        try {
            return userRepo.addUser(u);
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean addTitleToWatchlist(User u, String id) {
        return userRepo.addTitleToWatchlist(u.getUsername(), id);
    }

    public Boolean removeTitleFromWatchlist(User u, String id) {
        return userRepo.removeTitleFromWatchlist(u.getUsername(), id);
    }

    public Boolean checkTitleExists(User u, String id) {
        return userRepo.checkTitleExistsInWatchlist(u.getUsername(), id);
    }

    public List<Media> getWatchlist(User u) {
        List<String> list = userRepo.getWatchlist(u.getUsername());
        List<Media> watchlist = list.stream()
                .map(id -> searchSvc.getMedia(id))
                .filter(media -> media != null)
                .collect(Collectors.toList());
        return watchlist;
    }
}
