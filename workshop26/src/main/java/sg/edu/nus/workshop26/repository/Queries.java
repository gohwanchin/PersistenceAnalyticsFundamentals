package sg.edu.nus.workshop26.repository;

public interface Queries {
    public static final String SQL_INSERT_POST = 
            "insert into post(photo, comment, uploader, mediatype) values (?, ?, ?, ?)";
    public static final String SQL_GET_POST_BY_POST_ID = "select * from post where post_id=?";
}