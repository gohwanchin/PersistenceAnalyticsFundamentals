package vttp2022.paf.assessment.server.repositories;

public interface Queries {
        public static final String SQL_GET_USER_BY_ID 
                        = "select * from user where user_id = ?";
        public static final String SQL_GET_USER_BY_USERNAME 
                        = "select * from user where username = ?";
        public static final String SQL_INSERT_USER 
                        = "insert into user(user_id, username, name) values (?, ?, ?)";
        public static final String SQL_INSERT_TASK 
                        = "insert into task(description, priority, due_date, user_id) values (?, ?, ?, ?)";
}
