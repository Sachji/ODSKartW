package cz.muni.fi.pb138.odskart.backend;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.rules.ExpectedException;
import org.junit.Rule;

/**
 *
 * @author Vojta
 */
public class MovieTest {

    Movie movie;

    @Before
    public void setUp() {
       movie = new Movie("new");
    }

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Test
    public void nameEmptyTest() {
        expected.expect(IllegalArgumentException.class);
        movie = new Movie("");
    }

    @Test
    public void nameNullTest() {
        expected.expect(IllegalArgumentException.class);
        movie = new Movie(null);
    }

    @Test
    public void setNameNullTest() {
        expected.expect(IllegalArgumentException.class);
        String name = null;
        movie.setName(name);
    }

    @Test
    public void setNameEmptyTest() {
        expected.expect(IllegalArgumentException.class);
        String name = "";       
        movie.setName(name);
    }

    @Test
    public void validMovieTest() {
        assertEquals("new", movie.getName());
    }

        

}
