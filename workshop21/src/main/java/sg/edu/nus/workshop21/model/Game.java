package sg.edu.nus.workshop21.model;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Game {
    private Integer gid;
    private String name;
    private Integer year;
    private Integer ranking;
    private Integer usersRated;
    private String url;
    private String image;

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Integer getUsersRated() {
        return usersRated;
    }

    public void setUsersRated(Integer usersRated) {
        this.usersRated = usersRated;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public static Game create(SqlRowSet result) {
        Game g = new Game();
        g.setGid(result.getInt("gid"));
        g.setName(result.getString("name"));
        g.setRanking(result.getInt("year"));
        g.setRanking(result.getInt("ranking"));
        g.setUsersRated(result.getInt("users_rated"));
        g.setUrl(result.getString("url"));
        g.setImage(result.getString("image"));
        return g;
    }
}
