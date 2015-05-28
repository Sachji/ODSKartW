package cz.muni.fi.pb138.odskart.backend;

import java.util.List;

/** An interface class for managing files
 *
 * @author Jiří Šácha
 */
public interface KartManager {
    
    /** A method for retrieving a list of categories
     *
     * @return                          a list of categories
     */
    List<Category> getCategoryList();
    /** A method that retrieves a list of all the mediums of a category
     *
     * @param category                  category to be used
     * @return                          a list of all the media of the category
     */
    List<Medium> getCategoryMediums(Category category);
    /** A method for finding a movie by its name
     *
     * @param movieName                     the name of a movie
     * @return                              a list of the movie's media
     */
    List<Medium> findMovie(String movieName);
    /** A method that retrieves a category by its ID
     *
     * @param id                        ID of a category
     * @return                          the category with the specified ID
     * @throws KartException            if a category with the specified ID does not exist
     */
    Category getCategory(int id) throws KartException;
    /** A method for retrieving media by category and medium IDs
     *
     * @param categoryID                        the ID of a category
     * @param mediumID                          the ID of a movie within 
     * @return                                  the given medium
     * @throws KartException                    if a medium with the given ID was not found
     */
    Medium getMedium(int categoryID, int mediumID) throws KartException;
    /** A method for adding a new category
     *
     * @param category                  category to be added
     * @throws KartException            if a category with the specified name already exists
     */
    void addCategory(Category category) throws KartException;
    /** A method for removing a category by its ID
     *
     * @param id                        ID of the category to be removed
     * @throws KartException            if the category does not exist or if trying to remove the last category
     */
    void removeCategory(int id) throws KartException;
    /** A method for adding media
     *
     * @param medium                    medium to be added
     * @throws KartException            if the medium has more movies than the category allows or if the medium has no movies
     */
    void addMedium(Medium medium) throws KartException;
    /** A method for moving media
     *
     * @param newCat                    the new category where the medium should be moved
     * @param medium                    medium to be moved
     * @throws KartException            
     */
    void moveMedium(Category newCat, Medium medium)  throws KartException;
    /** A method for removing media
     *
     * @param medium                    medium to be removed
     * @throws KartException            if a category with the specified ID does not exist
     */
    void removeMedium(Medium medium) throws KartException;
    /** A method for saving the changes done to a file
     *
     * @throws KartException                    if the file was unable to be saved
     */
    void saveFile() throws KartException;
}
