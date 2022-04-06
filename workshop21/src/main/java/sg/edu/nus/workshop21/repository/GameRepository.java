package sg.edu.nus.workshop21.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.edu.nus.workshop21.model.Comment;
import sg.edu.nus.workshop21.model.Game;

import static sg.edu.nus.workshop21.repository.Queries.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class GameRepository {

    @Autowired
    private JdbcTemplate template;

    public List<Comment> getCommentsByGid(Integer gid, Integer limit, Integer offset) {
        final SqlRowSet result = template.queryForRowSet(SQL_SELECT_COMMENTS_BY_GID, gid, limit, offset);
        List<Comment> list = new ArrayList<>();
        while (result.next()) {
            list.add(Comment.create(result));
        }
        return list;
    }

    public Optional<Game> getGameByGid(Integer queryGid) {
        final SqlRowSet result = template.queryForRowSet(SQL_SELECT_GAME_BY_GID, queryGid);
        if (!result.next())
            return Optional.empty();
        return Optional.of(Game.create(result));
    }
}
