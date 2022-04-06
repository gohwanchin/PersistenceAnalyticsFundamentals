package sg.edu.nus.day23.repository;

import static sg.edu.nus.day23.repository.Queries.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.edu.nus.day23.model.Friend;

@Repository
public class AddressBookRepository {

    @Autowired
    private JdbcTemplate template;

    public Boolean checkExists(String value) {
        final SqlRowSet rs = template.queryForRowSet(SQL_SELECT_FRIEND_BY_EMAIL, value);
        return rs.first();
    }

    public Boolean addFriend(Friend f) {
        int added = template.update(SQL_INSERT_FRIEND, f.getEmail(),
                f.getName(), f.getPhone(), f.getDob(), f.getStatus(), f.getPassphrase());
        return added > 0;
    }

    public List<Friend> getFriends() {
        final List<Friend> list = new ArrayList<>();
        final SqlRowSet rs = template.queryForRowSet(SQL_GET_ALL_FRIENDS);
        while (rs.next()) {
            Friend f = Friend.create(rs);
            list.add(f);
        }
        return list;
    }
}
