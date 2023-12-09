package vttp2022.paf.assessment.server.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import vttp2022.paf.assessment.server.models.User;
import vttp2022.paf.assessment.server.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    public Optional<User> findUserByUserId(String userId) {
        Optional<SqlRowSet> opt = userRepo.getUserById(userId);
        if (opt.isEmpty())
            return Optional.empty();
        SqlRowSet rs = opt.get();
        User u = new User();
        u.setUserId(rs.getString("user_id"));
        u.setUsername(rs.getString("username"));
        u.setName(rs.getString("name"));
        return Optional.of(u);
    }

    public Optional<User> findUserByUsername(String username) {
        Optional<SqlRowSet> opt = userRepo.getUserByUsername(username);
        if (opt.isEmpty())
            return Optional.empty();
        SqlRowSet rs = opt.get();
        User u = new User();
        u.setUserId(rs.getString("user_id"));
        u.setUsername(rs.getString("username"));
        u.setName(rs.getString("name"));
        return Optional.of(u);
    }

    public String insertUser(User user) {
        String id = UUID.randomUUID().toString().substring(0, 8);
        user.setUserId(id);
        if (!userRepo.insertUser(user))
            throw new IllegalArgumentException("Unable to add user");
        return id;
    }
}
