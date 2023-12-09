package sg.edu.nus.mini_project.model;

import jakarta.json.JsonObject;

public class Media {
    private String title;
    private String year;
    private String id;
    private String type;
    private String poster;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public static Media create(JsonObject obj) {
        Media m = new Media();
        m.setTitle(obj.getString("Title"));
        m.setYear(obj.getString("Year"));
        m.setId(obj.getString("imdbID"));
        m.setType(obj.getString("Type"));
        m.setPoster(obj.getString("Poster"));
        return m;
    }
}
