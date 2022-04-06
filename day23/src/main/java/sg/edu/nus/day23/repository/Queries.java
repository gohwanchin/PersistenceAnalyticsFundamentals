package sg.edu.nus.day23.repository;

public interface Queries {
    public static final String SQL_SELECT_FRIEND_BY_EMAIL = "select * from bff where email = ?";
    public static final String SQL_INSERT_FRIEND 
        = "insert into bff (email, name, phone, dob, status, passphrase) values(?, ?, ?, ?, ?, sha1(?))";
    public static final String SQL_GET_ALL_FRIENDS = "select * from bff";
}
