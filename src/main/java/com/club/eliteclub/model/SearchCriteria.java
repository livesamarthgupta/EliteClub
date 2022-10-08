package com.club.eliteclub.model;

import javax.validation.constraints.Pattern;

public class SearchCriteria {

    @Pattern(regexp = "(\\w+)")
    private String searchTerm;

    @Pattern(regexp = "/[1-4]/")
    private Integer rating;

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
