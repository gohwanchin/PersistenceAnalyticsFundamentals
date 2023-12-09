package sg.edu.nus.mini_project.model;

import java.util.List;

public class User {
    private String username;
    private String password;
    private List<Media> watchlist;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Media> getWatchlist() {
        return watchlist;
    }

    public void setWatchlist(List<Media> watchlist) {
        this.watchlist = watchlist;
    }
}
