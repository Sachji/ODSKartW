package cz.muni.fi.pb138.odskart.backend;

import java.util.List;
import java.util.ArrayList;

public class Category {

    private Integer id;
    private final List<Medium> mediums;

    public Category() {
        this.id = null;
        this.mediums = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
