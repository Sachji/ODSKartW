package cz.muni.fi.pb138.odskart.backend;


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

}
