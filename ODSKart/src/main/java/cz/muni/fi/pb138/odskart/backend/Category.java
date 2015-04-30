package cz.muni.fi.pb138.odskart.backend;

public class Category {

    private final int id;
    private final String name;
    private final int maxMediumMovies;

    public Category(Integer id, int maxMediumMovies, String name) {
        if (maxMediumMovies <= 0) {
            throw new IllegalArgumentException("maximum of medium movies must be positive non-zero number");
        }
        this.name = name;
        this.id = id;
        this.maxMediumMovies = maxMediumMovies;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMaxMediumMovies() {
        return maxMediumMovies;
    }
}
