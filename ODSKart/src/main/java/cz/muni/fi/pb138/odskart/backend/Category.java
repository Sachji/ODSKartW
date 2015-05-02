package cz.muni.fi.pb138.odskart.backend;

public class Category {

    private Integer id;
    private final String name;
    private final int maxMediumMovies;

    public Category(Integer id, int maxMediumMovies, String name) {
        if (maxMediumMovies <= 0) {
            throw new IllegalArgumentException("Maximum of medium movies must be positive non-zero number.");
        }
        if(name == null || name.equals("")){
            throw new IllegalArgumentException("Name cannot be empty string.");
        }
        this.name = name;
        this.id = id;
        this.maxMediumMovies = maxMediumMovies;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public int getMaxMediumMovies() {
        return maxMediumMovies;
    }
}
