package sg.edu.nus.mini_project.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.edu.nus.mini_project.model.User;

@Repository
public class UserRepository implements Queries {

    @Autowired
    private JdbcTemplate template;

    public Boolean userLogin(User u) {
        SqlRowSet rs = template.queryForRowSet(SQL_GET_USER_BY_USERNAME_AND_PASS, u.getUsername(), u.getPassword());
        return rs.next();
    }

    public Boolean addUser(User u) {
        int added = template.update(SQL_ADD_USER, u.getUsername(), u.getPassword());
        return added > 0;
    }

    public Boolean addTitleToWatchlist(String username, String id) {
        int added = template.update(SQL_ADD_TITLE_TO_WATCHLIST, username, id, new Date());
        return added > 0;
    }

    public Boolean removeTitleFromWatchlist(String username, String id) {
        int removed = template.update(SQL_REMOVE_TITLE_FROM_WATCHLIST, username, id);
        return removed > 0;
    }

    public Boolean checkTitleExistsInWatchlist(String username, String id) {
        SqlRowSet rs = template.queryForRowSet(SQL_CHECK_TITLE_EXISTS_IN_WATCHLIST, username, id);
        return rs.next();
    }

    public List<String> getWatchlist(String username) {
        List<String> list = new ArrayList<>();
        SqlRowSet rs = template.queryForRowSet(SQL_GET_WATCHLIST_BY_USER, username);
        while(rs.next())
            list.add(rs.getString("id"));
        return list;
    }
}
