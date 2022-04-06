package sg.edu.nus.workshop21.model;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Comment {
    private String cid;
    private String user;
    private Integer rating;
    private String cText;
    private Integer gid;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getcText() {
        return cText;
    }

    public void setcText(String cText) {
        this.cText = cText;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public static Comment create(SqlRowSet result) {
        Comment c = new Comment();
        c.setCid(result.getString("c_id"));
        c.setUser(result.getString("user"));
        c.setRating(result.getInt("rating"));
        c.setcText(result.getString("c_text"));
        c.setGid(result.getInt("gid"));
        return c;
    }
}
