package cz.muni.fi.pb138.odskart.backend;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.Rule;
import static org.junit.Assert.*;

public class CategoryTest {

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Test
    public void nameEmptyTest() {
        expected.expect(IllegalArgumentException.class);
        Category category = new Category(1, 1, "");
    }

    @Test
    public void nameNullTest() {
        expected.expect(IllegalArgumentException.class);
        Category category = new Category(1, 1, null);
    }

    @Test
    public void invalidMaxMediumMoviesTest() {
        expected.expect(IllegalArgumentException.class);
        Category category = new Category(1, 0, "a");
    }

    @Test
    public void validCategoryTest() {
        Integer id = 1;
        Category category = new Category(id, 1, "a");
        assertEquals(id, category.getId());
        assertEquals(1, category.getMaxMediumMovies());
        assertEquals("a", category.getName());
    }

}
