package cz.muni.fi.pb138.odskart.backend;

import java.util.List;
import java.util.ArrayList;

public class Category {

    private final int id;
    private final String name;
    private final int maxMediumMovies;
    private final List<Medium> mediums;
    
    public Category(Integer id, int maxMediumMovies, String name) {
        if(maxMediumMovies <= 0){
            throw new IllegalArgumentException("maximum of medium movies must be positive non-zero number");
        }
        this.name = name;
        this.id = id;
        this.maxMediumMovies = maxMediumMovies;
        this.mediums = new ArrayList<>();
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

    public List<Medium> getMediums() {
        return mediums;
    }

    public void addMedium(Medium medium) {
        this.mediums.add(medium);
    }

    public Integer findMovie(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Movie name is null");
        }

        throw new UnsupportedOperationException();
    }

}
