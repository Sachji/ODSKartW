package cz.muni.fi.pb138.odskart.backend;

public class Movie {

    private String name;

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
