/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pb138.odskart.backend;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
/**
 *
 * @author Jiří
 */
public class Medium {
   
   private Integer id;
   private List<Movie> movies;

    public Medium(Integer id, List<Movie> movies) {
        this.id = id;
        this.movies = movies;
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
        final Medium other = (Medium) obj;
        if (!Objects.equals(this.movies, other.movies)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        
        StringBuilder listString = new StringBuilder();
       for ( Movie s : movies)
           listString.append(s.getName() + " ");
       
        return "Medium{" + "movies=" + listString + '}';
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
    // find movie 
    public Integer findMovie(String name){
        if (name == null) throw new IllegalArgumentException("Movie name is null");
        
        // if found return index otherwise -1
        return movies.indexOf(new Movie(name));
    }
    
    
    
   
    
}
