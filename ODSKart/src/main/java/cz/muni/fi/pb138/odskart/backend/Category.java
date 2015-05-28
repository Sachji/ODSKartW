package cz.muni.fi.pb138.odskart.backend;

/**
 * A class describing categories
 *
 * @author Jiří Šácha, Ondřej Skýba
 */
public class Category {

    private Integer id;
    private final String name;
    private final int maxMediumMovies;

    /**
     * A constructor for a category
     *
     * @param id id of a category
     * @param maxMediumMovies the maximum number of movies for a medium, must be
     * positive
     * @param name the name for a category, cannot be null
     */
    public Category(Integer id, int maxMediumMovies, String name) {
        if (maxMediumMovies <= 0) {
            throw new IllegalArgumentException("Maximum of medium movies must be positive non-zero number.");
        }
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("Name cannot be empty string.");
        }
        this.name = name;
        this.id = id;
        this.maxMediumMovies = maxMediumMovies;
    }

    /**
     * A getter method for the id of a category
     *
     * @return the id of a category
     */
    public Integer getId() {
        return id;
    }

    /**
     * A setter method for the id of a category
     *
     * @param id the id of a category to be set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * A getter method for the name of a category
     *
     * @return the name of a category
     */
    public String getName() {
        return name;
    }

    /**
     * A getter method for the maximum number of movies for a medium in a
     * category
     *
     * @return the maximum number of movies for a medium in a category
     */
    public int getMaxMediumMovies() {
        return maxMediumMovies;
    }

    /**
     * A toString method for this class
     *
     * @return toString representation of a category
     */
    @Override
    public String toString() {
        return "Category{" + "id=" + id + ", name=" + name + ", maxMediumMovies=" + maxMediumMovies + '}';
    }

}
