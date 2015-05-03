package cz.muni.fi.pb138.odskart.frontend;

import cz.muni.fi.pb138.odskart.backend.Category;
import cz.muni.fi.pb138.odskart.backend.KartException;
import cz.muni.fi.pb138.odskart.backend.Medium;
import javax.servlet.http.HttpServletRequest;
import cz.muni.fi.pb138.odskart.backend.KartManager;
import cz.muni.fi.pb138.odskart.backend.Movie;
import java.util.ArrayList;

public class AddMediumForm {
    
    private Category category;
    private final ArrayList<Movie> movies;
    
    public AddMediumForm() {
        movies = new ArrayList<>();
    }
    
    public static AddMediumForm extractFromRequest(HttpServletRequest request, KartManager manager) throws KartException {
        AddMediumForm form = new AddMediumForm();
        int id = Integer.parseInt(request.getParameter("category_id"));
        Category cat = manager.getCategory(id);
        form.setCategory(cat);
        
        for (int i = 1; i <= cat.getMaxMediumMovies(); i++) {
            String name = request.getParameter("movie" + i);
            if(!name.equals("")){
                form.addMovie(new Movie(name));
            }
        }
        
        return form;
    }
    
    public Medium validateAndGet(StringBuilder error) {
        try {
            Medium medium = new Medium(getCategory());
            for(Movie movie : movies){
                medium.addMovie(movie);
            }
            return medium;
        } catch (IllegalArgumentException | NullPointerException ex) {
            error.append(ex.getMessage());
            return null;
        }
        
    }
    
    public Category getCategory() {
        return category;
    }
    
    public void setCategory(Category category) {
        this.category = category;
    }
    
    public void addMovie(Movie m) {
        this.movies.add(m);
    }
    
}
