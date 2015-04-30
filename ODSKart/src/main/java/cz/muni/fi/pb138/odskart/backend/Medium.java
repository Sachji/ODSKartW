package cz.muni.fi.pb138.odskart.backend;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class Medium {

    private Integer id;
    private final List<Movie> movies;
    private Category category;

    public Medium(Category category) {
        this.id = null; // ID is not always known at point of creating instance
        this.movies = new ArrayList<>();
        this.category = category;
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

    public Integer findMovie(String name) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("Movie name is null or empty string");
        }

        // if found return index otherwise -1
        return movies.indexOf(new Movie(name));
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.movies);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Medium)) {
            return false;
        }
        // TODO

        return true;
    }

    @Override
    public String toString() {

        StringBuilder listString = new StringBuilder();
        for (Movie s : movies) {
            listString.append(s.getName()).append(" ");
        }

        return "Medium{" + "movies=" + listString + '}';
    }
}
