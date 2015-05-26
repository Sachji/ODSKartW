package cz.muni.fi.pb138.odskart.backend;

import java.util.Objects;


public final class Movie {

    private String name;

    

    public Movie(String name) {        
        setName(name);
    }

    public String getName() {
        return name;
    }

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
        if (!getName().equals(other.getName())) {
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

}
