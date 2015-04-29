package cz.muni.fi.pb138.odskart.backend;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class Medium {

    private Integer id;
    private final List<Movie> movies;

    public Medium() {
        this.id = null; // ID is not always known at point of creating instance
        this.movies = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        if (getClass() != obj.getClass()) {
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

    public List<Movie> getMovies() {
        return movies;
    }

    public void addMovie(Movie movie) {
        this.movies.add(movie);
    }

    // find movie 

    public Integer findMovie(String name) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("Movie name is null or empty string");
        }

        // if found return index otherwise -1
        return movies.indexOf(new Movie(name));
    }

}
