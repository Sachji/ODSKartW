package cz.muni.fi.pb138.odskart.backend;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.rules.ExpectedException;
import org.junit.Rule;

/**
 *
 * @author Vojta
 */
public class MediumTest {

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Test
    public void categoryNullTest() {
        expected.expect(IllegalArgumentException.class);
        Category category = null;
        Medium medium = new Medium(category);
    }

    @Test
    public void mediumNullTest() {
        expected.expect(IllegalArgumentException.class);
        Medium medium = null;
        Medium medium2 = new Medium(medium);
    }

    @Test
    public void idIsLessEqualZeroTest() {        
        Medium medium = new Medium(new Category(1, 1, "new"));
        Integer id = 0;
        expected.expect(IllegalArgumentException.class);
        medium.setId(id);
    }

    @Test
    public void setCategoryNullTest() {
        Category category = null;
        Medium medium = new Medium(new Category(1, 1, "new"));
        expected.expect(IllegalArgumentException.class);
        medium.setCategory(category);
    }

    @Test
    public void getMovieNameNullTest() {
        
        String name = null;
        Medium medium = new Medium(new Category(1, 1, "new"));
        expected.expect(IllegalArgumentException.class);
        medium.getMovie(name);
    }

    @Test
    public void getMovieNameEmptyTest() {
        
        String name = "";
        Medium medium = new Medium(new Category(1, 1, "new"));
        expected.expect(IllegalArgumentException.class);
        medium.getMovie(name);
    }

    @Test
    public void validMediumTest() {
        Medium medium = new Medium(new Category(1, 1, "new"));
        List<Movie> movies = new ArrayList<>();
        Category category = new Category(1, 1, "new");
        Movie movie1 = new Movie("1");
        Movie movie2 = new Movie("2");
        movies.add(movie1);
        movies.add(movie2);
        Integer id = 1;
        medium.setId(id);
        medium.addMovie(movie1);
        medium.addMovie(movie2);
        medium.setCategory(category);
        assertEquals(id, medium.getId());
        assertEquals(movies, medium.getMovies());
        assertEquals(category, medium.getCategory());
    }

}
