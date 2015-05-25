package cz.muni.fi.pb138.odskart.backend;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.rules.ExpectedException;
import org.junit.Rule;

/**
 *
 * @author Vojta
 */
public class MovieTest {
    
    @Rule
    public ExpectedException expected = ExpectedException.none();
    
    @Test
    public void nameEmptyTest() {
        expected.expect(IllegalArgumentException.class);
        Movie movie = new Movie("");
    }
    
    @Test
    public void nameNullTest(){
        expected.expect(IllegalArgumentException.class);
        Movie movie = new Movie(null);
    }
    
    @Test
    public void validMovieTest(){
        Movie movie = new Movie("new");
        assertEquals("new",movie.getName());
    }
    

    /**
     * Test of hashCode method, of class Movie.
     */
    @Test
    public void testHashCode() {

        Movie instance = new Movie("new");
        int expResult = instance.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Movie.
     */
    @Test
    public void testEquals() {

        Object obj = new Movie("new");
        Movie instance = new Movie("new");
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

    }

    /**
     * Test of getName method, of class Movie.
     */
    @Test
    public void testGetName() {

        Movie instance = new Movie("new");
        String expResult = "new";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Movie.
     */
    @Test
    public void testSetName() {

        String name = "";
        String name2 = null;
        Movie instance = new Movie("new");
        instance.setName("name");
        assertFalse("Must not be empty",instance.getName().equals(name));
        assertFalse("Must not be null",instance.getName().equals(name2));
    }
    
}