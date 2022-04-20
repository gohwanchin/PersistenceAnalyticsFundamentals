package sg.edu.nus.workshop26.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Post {
    private Integer postId;
    private String uploader;
    private String comment;
    private String imageType;
    private String image;

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public static Post create(ResultSet rs) throws SQLException {
        Post p = new Post();
        p.setPostId(rs.getInt("post_id"));
        p.setComment(rs.getString("comment"));
        p.setImageType(rs.getString("mediatype"));
        p.setImage(rs.getString("photo"));
        p.setUploader(rs.getString("uploader"));
        return p;
    }
}
