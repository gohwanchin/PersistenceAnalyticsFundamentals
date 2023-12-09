package vttp2022.paf.assessment.server.repositories;

import static vttp2022.paf.assessment.server.repositories.Queries.*;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2022.paf.assessment.server.models.User;

@Repository
public class UserRepository {
    @Autowired private JdbcTemplate template;

    public Optional<SqlRowSet> getUserById(String userId) {
        SqlRowSet rs = template.queryForRowSet(SQL_GET_USER_BY_ID, userId);
        if (rs.next())
            return Optional.of(rs);
        return Optional.empty();
    }
    
    public Optional<SqlRowSet> getUserByUsername(String username) {
        SqlRowSet rs = template.queryForRowSet(SQL_GET_USER_BY_USERNAME, username);
        if (rs.next())
            return Optional.of(rs);
        return Optional.empty();
    }

    public boolean insertUser(User user){
        int added = template.update(SQL_INSERT_USER, user.getUserId(), user.getUsername(), user.getName());
        return added > 0;
    }
}
