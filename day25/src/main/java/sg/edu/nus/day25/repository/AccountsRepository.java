package sg.edu.nus.day25.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.edu.nus.day25.model.User;

import static sg.edu.nus.day25.repository.Queries.*;

@Repository
public class AccountsRepository {

    @Autowired
    JdbcTemplate template;

    public Boolean userLogin(User u) {
        SqlRowSet rs 
            = template.queryForRowSet(SQL_GET_USER_BY_USERNAME_AND_PASS, u.getUsername(), u.getPassword());
        return rs.next();
    }
}
