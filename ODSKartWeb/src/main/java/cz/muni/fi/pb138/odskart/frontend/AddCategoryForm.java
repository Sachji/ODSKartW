package cz.muni.fi.pb138.odskart.frontend;

import cz.muni.fi.pb138.odskart.backend.Category;
import javax.servlet.http.HttpServletRequest;

public class AddCategoryForm {

    private String name;
    private String maxMediumMovies;
    
    public static AddCategoryForm extractFromRequest(HttpServletRequest request) {
        AddCategoryForm form = new AddCategoryForm();
        form.setName(request.getParameter("category_name"));
        form.setMaxMediumMovies(request.getParameter("maxMovies"));
        System.out.println("Name: " + request.getParameter("category_name"));
        System.out.println("Max: " + request.getParameter("maxMovies"));
        return form;
    }

    public Category validateAndGet(StringBuilder error) {
        try {
            if(getMaxMediumMovies().trim().equals("")){
                throw new IllegalArgumentException("Max medium movies not set.");
            }
            int max = Integer.parseInt(getMaxMediumMovies());
            Category category = new Category(null, max, getName());
            return category;
        } catch (IllegalArgumentException | NullPointerException ex) {
            error.append(ex.getMessage());
            return null;
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaxMediumMovies() {
        return maxMediumMovies;
    }

    public void setMaxMediumMovies(String maxMediumMovies) {
        this.maxMediumMovies = maxMediumMovies;
    }

}
