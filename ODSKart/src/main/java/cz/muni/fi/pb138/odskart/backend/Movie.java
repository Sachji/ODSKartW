package cz.muni.fi.pb138.odskart.backend;

import java.util.Objects;

/**
 * A class for managing movies
 *
 * @author Jiří Šácha, Ondřej Skýba
 */
public final class Movie {

    private String name;

    /**
     * A constructor for a movie
     *
     * @param name the name of a movie, cannot be null or empty string
     */
    public Movie(String name) {
        setName(name);
    }

    /**
     * A getter method for retrieving the name of a movie
     *
     * @return the name of a movie
     */
    public String getName() {
        return name;
    }

    /**
     * A setter method for the name of a movie
     *
     * @param name the name to be set
     */
    public final void setName(String name) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("name is null or empty string");
        }
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Movie)) {
            return false;
        }
        Movie other = (Movie) obj;
        if (!getName().toLowerCase().equals(other.getName().toLowerCase())) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.name);
        return hash;
    }

    /**
     * A toString method for this class
     *
     * @return toString representation of a movie
     */
    @Override
    public String toString() {
        return "Movie{" + "name=" + name + '}';
    }

}
