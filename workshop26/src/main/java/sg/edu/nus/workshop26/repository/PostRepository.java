package sg.edu.nus.workshop26.repository;

import static sg.edu.nus.workshop26.repository.Queries.SQL_GET_POST_BY_POST_ID;
import static sg.edu.nus.workshop26.repository.Queries.SQL_INSERT_POST;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import sg.edu.nus.workshop26.model.Post;

@Repository
public class PostRepository {

    @Autowired
    private JdbcTemplate template;

    public Integer insertPost(Post p) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(SQL_INSERT_POST, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,p.getImage());
            ps.setString(2,p.getComment());
            ps.setString(3,p.getUploader());
            ps.setString(4,p.getImageType());
            return ps;
        }, keyHolder);
        Integer added = keyHolder.getKey().intValue();
        /* Integer added = template.update(SQL_INSERT_POST, p.getImage(), p.getComment(), p.getUploader(),
                p.getImageType()); */
        return added;
    }
    
    public Optional<Post> getPostById(Integer postId) {
        return template.query(SQL_GET_POST_BY_POST_ID,rs ->{
            if(!rs.next())
                return Optional.empty();
            Post post = Post.create(rs);
            return Optional.of(post);
        },postId);        
    }
}
