package cz.muni.fi.pb138.odskart.backend;

import java.util.List;

public interface KartManager {
    
    List<Category> getCategoryList();
    List<Medium> getCategoryMediums(Category category);
    void addCategory(String name, int maxMediumMovies) throws KartException;
    void removeCategory(int id) throws KartException;
    void addMedium(Medium medium) throws KartException;
    void moveMedium(Category oldCat, Category newCat, Medium medium)  throws KartException;
    void removeMedium( Medium medium) throws KartException;
}
