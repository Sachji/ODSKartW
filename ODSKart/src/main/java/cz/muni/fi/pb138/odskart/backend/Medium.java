package cz.muni.fi.pb138.odskart.backend;

import java.util.List;
import java.util.ArrayList;

public class Medium {

    private Integer id;
    private final List<Movie> movies;
    private Category category;

    public Medium(Category category) {
        this.id = null;
        this.movies = new ArrayList<>();
        this.category = category;
    }
    
    public Medium(Medium medium) {
        this.id = medium.getId();
        this.category = medium.getCategory();
        this.movies = new ArrayList<>();
        for(Movie m : medium.movies) {
            this.movies.add(m);
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void addMovie(Movie movie) {
        this.movies.add(movie);
    }

    public Integer getMovie(String name) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("Movie name is null or empty string");
        }
        // if found return index otherwise -1
        return movies.indexOf(new Movie(name));
    }

    @Override
    public String toString() {

        StringBuilder listString = new StringBuilder();
        for (Movie s : movies) {
            listString.append(s.getName()).append(",");
        }
        listString.deleteCharAt(listString.length() - 1);

        return "Medium{" + "movies=" + listString + '}';
    }
}
