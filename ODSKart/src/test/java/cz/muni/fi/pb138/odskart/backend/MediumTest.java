/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        expected.expect(IllegalArgumentException.class);
        Medium medium = new Medium(new Category(1, 1, "new"));
        Integer id = 0;
        medium.setId(id);
    }

    @Test
    public void setCategoryNullTest() {
        expected.expect(IllegalArgumentException.class);
        Category category = null;
        Medium medium = new Medium(new Category(1, 1, "new"));
        medium.setCategory(category);
    }

    @Test
    public void getMovieNameNullTest() {
        expected.expect(IllegalArgumentException.class);
        String name = null;
        Medium medium = new Medium(new Category(1, 1, "new"));
        medium.getMovie(name);
    }

    @Test
    public void getMovieNameEmptyTest() {
        expected.expect(IllegalArgumentException.class);
        String name = "";
        Medium medium = new Medium(new Category(1, 1, "new"));
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

    /**
     * Test of getId method, of class Medium.
     */
    @Test
    public void testGetId() {

        Medium instance;
        instance = new Medium(new Category(5, 2, "new"));
        instance.setId(5);
        Integer expResult = 5;
        Integer result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Medium.
     */
    @Test
    public void testSetId() {

        Integer id = 4;
        Medium instance;
        instance = new Medium(new Category(5, 2, "new"));
        instance.setId(id);
        assertEquals(instance.getId(), id);
    }

    /**
     * Test of getCategory method, of class Medium.
     */
    @Test
    public void testGetCategory() {

        Medium instance;
        Category category = new Category(4, 2, "new");
        instance = new Medium(new Category(2, 2, "new"));
        Category expResult = category;
        instance.setCategory(category);
        Category result = instance.getCategory();
        assertEquals(expResult, result);

    }

    /**
     * Test of setCategory method, of class Medium.
     */
    @Test
    public void testSetCategory() {

        Category category = new Category(5, 2, "new");
        Medium instance = new Medium(new Category(2, 2, "new"));
        instance.setCategory(category);
        assertEquals(instance.getCategory(), category);
    }

    /**
     * Test of getMovies method, of class Medium.
     */
    @Test
    public void testGetMovies() {

        Medium instance;

        List<Movie> movies = new ArrayList<>();
        Movie movie1 = new Movie("new1");
        Movie movie2 = new Movie("new2");
        movies.add(movie1);
        movies.add(movie2);
        instance = new Medium(new Category(2, 2, "new"));
        instance.addMovie(movie1);
        instance.addMovie(movie2);
        List<Movie> expResult = movies;
        List<Movie> result = instance.getMovies();
        assertEquals(expResult, result);
    }

    /**
     * Test of addMovie method, of class Medium.
     */
    @Test
    public void testAddMovie() {

        Movie movie = new Movie("new");
        Movie movie2 = new Movie("new2");
        List<Movie> movies = new ArrayList<>();
        movies.add(movie);
        movies.add(movie2);
        Medium instance = new Medium(new Category(2, 2, "new"));
        instance.addMovie(movie);
        instance.addMovie(movie2);
        assertEquals(instance.getMovies(), movies);

    }

    /**
     * Test of getMovie method, of class Medium.
     */
    @Test
    public void testGetMovie() {
        String name = "new";
        String name1 = "";
        String name2 = null;
        Movie movie = new Movie("new");
        Movie movie2 = new Movie("new2");
        List<Movie> movies = new ArrayList<>();
        movies.add(movie);
        movies.add(movie2);
        Medium instance = new Medium(new Category(2, 2, "new"));
        instance.addMovie(movie);
        instance.addMovie(movie2);
        Integer expResult = movies.indexOf(movie);
        Integer result = instance.getMovie(name);
        assertEquals(expResult, result);
        assertFalse("Must not be empty", name.equals(name1));
        assertFalse("Must not be null", name.equals(name2));
    }

}
