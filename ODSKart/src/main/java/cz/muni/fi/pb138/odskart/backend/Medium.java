package cz.muni.fi.pb138.odskart.backend;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

/** A class for managing media
 *
 * @author Jiří Šácha
 */
public class Medium {

    private Integer id;
    private final List<Movie> movies;
    private Category category;
    
    /** A constructor for a medium based on its category
     *
     * @param category                      category of a medium, cannot be null
     */
    public Medium(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Category is null");
        }
        this.id = null;
        this.movies = new ArrayList<>();
        this.category = category;
        
    }
    
    /** A constructor for a medium
     *
     * @param medium                        a specified medium, cannot be null
     */
    public Medium(Medium medium) {
        if (medium == null) {
            throw new IllegalArgumentException("Medium is null");
        }
        this.id = medium.getId();
        this.category = medium.getCategory();
        this.movies = new ArrayList<>();
        for(Movie m : medium.movies) {
            this.movies.add(m);
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Medium)) {
            return false;
        }
        Medium other = (Medium) obj;
        if (!getId().equals(other.getId())) {
            return false;
        } else {
            return true;
        }
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
        return hash;
    }
    
    /** A getter method for the id of a medium
     *
     * @return                              the id of a medium
     */
    public Integer getId() {
        return id;
    }
    
    /** A setter method for the id of a medium
     *
     * @param id                            the id to be set, cannot be lower than zero or null
     */
    public void setId(Integer id) {
        if (id <= 0 || id == null ) {
            throw new IllegalArgumentException("Id is less than zero or null");
        }
        this.id = id;
    }

    /** A getter method for the category of a medium
     *
     * @return                              the category of a medium
     */
    public Category getCategory() {
        return category;
    }

    /** A setter method for the category of a medium
     *
     * @param category                      category to be set, cannot be null
     */
    public void setCategory(Category category) {
        if (category == null ) {
            throw new IllegalArgumentException("Category is null");
        }
        this.category = category;
    }
    
    /** A getter method for the movies of a medium
     *
     * @return                              a list of the movies of a medium
     */
    public List<Movie> getMovies() {
        return movies;
    }
    
    /** A method that adds a movie to a medium
     *
     * @param movie                         movie to be added, cannot be null
     */
    public void addMovie(Movie movie) {
        if (movie == null ) {
            throw new IllegalArgumentException("Movie is null");
        }
        this.movies.add(movie);
    }
    
    /** A getter method for a movie based on its name
     *
     * @param name                          the name of a movie
     * @return                              the specified movie
     * @throws IllegalArgumentException     if the movie name is not specified or null
     */
    public Integer getMovie(String name) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("Movie name is null or empty string");
        }
        // if found return index otherwise -1
        return movies.indexOf(new Movie(name));
    }

    /** toString method of this class
     *
     * @return                          toString representation of media and movies
     */
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
