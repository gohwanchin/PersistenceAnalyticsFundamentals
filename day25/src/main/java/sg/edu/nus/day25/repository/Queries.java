package sg.edu.nus.day25.repository;

public interface Queries {
    public static final String SQL_GET_USER_BY_USERNAME_AND_PASS = "select * from user where username = ? and password = sha1(?)";
}
