package sg.edu.nus.workshop21.repository;

public interface Queries {
    public static final String SQL_SELECT_GAME_BY_GID = "select * from game where gid = ?";
    public static final String SQL_SELECT_COMMENTS_BY_GID = "select * from comment where gid = ? limit ? offset ?";
}
