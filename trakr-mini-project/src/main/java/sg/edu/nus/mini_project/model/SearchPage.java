package sg.edu.nus.mini_project.model;

import java.util.List;

public class SearchPage {
    private Integer page = 1;
    private String query;
    private Integer totalResults;
    private Double totalPages;
    private List<Media> results;
    
    public SearchPage(Integer page, String query) {
        this.page = page;
        this.query = query;
    }

    public Integer getPage() {
        return page;
    }
    
    public void setPage(Integer page) {
        this.page = page;
    }
    
    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
    
    public Integer getTotalResults() {
        return totalResults;
    }
    
    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public void setTotalResultsandPages(Integer totalResults) {
        this.totalResults = totalResults;
        this.totalPages = Math.ceil(totalResults/10d);
    }
    
    public Double getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Double totalPages) {
        this.totalPages = totalPages;
    }

    public List<Media> getResults() {
        return results;
    }

    public void setResults(List<Media> results) {
        this.results = results;
    }
}
