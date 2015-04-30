package cz.muni.fi.pb138.odskart.backend;

import java.io.IOException;
import java.util.List;


public interface SpreadSheetManager {
    
    List<Category> getCategoryList();
    void addCategory(String name, int maxMediumMovies) throws KartException, IOException;
    void removeCategory(int id) throws KartException, IOException;
    
}
