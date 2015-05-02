package cz.muni.fi.pb138.odskart.backend;

import java.util.Objects;

public class Movie {

    private String name;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.name);
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
        final Movie other = (Movie) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    public Movie(String name) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("name is null or empty string");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("name is null or empty string");
        }
        this.name = name;
    }

}
