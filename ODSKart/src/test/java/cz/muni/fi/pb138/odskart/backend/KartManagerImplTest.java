/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb138.odskart.backend;

import java.io.File;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.jopendocument.dom.spreadsheet.SpreadSheet;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author Vojta
 */
public class KartManagerImplTest {

    private File file;
    private KartManagerImpl kartM;

    @Before
    public void setUp() throws IOException {
        final Object[][] data = new Object[4][3];
        data[0] = new Object[]{1, "Spartacus", "Armagedon"};
        data[1] = new Object[]{2, "Lotr", "Hobbit"};
        data[2] = new Object[]{3, "300", "Godfather"};
        data[3] = new Object[]{4, "Matrix"};

        String[] columns = new String[]{"ID", "Movie#1", "Movie#2"};
        TableModel model = new DefaultTableModel(data, columns);
        file = folder.newFile("TestFile.ods");
        SpreadSheet.createEmpty(model).saveAs(file);
        kartM = new KartManagerImpl(file);
    }

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Test
    public void wrongIdNumber() throws IOException, KartException {
        expected.expect(KartException.class);
        Integer id = 2;
        Category category = kartM.getCategory(id);
    }

    @Test
    public void fileIsNull() throws IOException {
        file = null;
        expected.expect(NullPointerException.class);
        kartM = new KartManagerImpl(file);
    }

    @Test
    public void categoryNameExistTest() throws KartException {
        Category category = new Category(1, 1, "new");
        Category category2 = new Category(2, 2, "new");
        kartM.addCategory(category);
        expected.expect(KartException.class);
        kartM.addCategory(category2);
    }

    @Test
    public void categoryNameNotExistTest() throws KartException {
        Category category = new Category(1, 1, "new");
        Category category2 = new Category(2, 2, "new2");
        kartM.addCategory(category);
        kartM.addCategory(category2);
        Category category3 = kartM.getCategory(2);
        assertEquals(category2.getName(), category3.getName());
    }

    @Test
    public void addCategoryIsNullTest() throws KartException {
        Category category = null;
        expected.expect(NullPointerException.class);
        kartM.addCategory(category);
    }

    @Test
    public void removingLastCategoryTest() throws KartException {
        int id = 0;
        expected.expect(KartException.class);
        kartM.removeCategory(id);
    }

    @Test
    public void removeCategoryTest() throws KartException {
        Category category = new Category(1, 1, "new");
        Category category2 = new Category(2, 2, "new2");
        int id = 2;
        kartM.addCategory(category);
        kartM.addCategory(category2);
        kartM.removeCategory(id);
        expected.expect(KartException.class);
        kartM.getCategory(id);
    }

    @Test
    public void mediumIsNullTest() throws KartException {
        Medium medium = null;
        expected.expect(IllegalArgumentException.class);
        kartM.addMedium(medium);
    }

    @Test
    public void mediumCategoryIsNullTest() throws KartException {
        Category category = null;
        expected.expect(IllegalArgumentException.class);
        Medium medium = new Medium(category);
        kartM.addMedium(medium);
    }

    @Test
    public void moreMoviesThanAbleTest() throws KartException {
        Medium medium = new Medium(new Category(1, 1, "new"));
        Movie movie1 = new Movie("new1");
        Movie movie2 = new Movie("new2");
        medium.addMovie(movie1);
        medium.addMovie(movie2);
        expected.expect(KartException.class);
        kartM.addMedium(medium);
    }

    @Test
    public void noMoviesOnMediumTest() throws KartException {
        Medium medium = new Medium(new Category(1, 1, "new"));
        expected.expect(KartException.class);
        kartM.addMedium(medium);
    }

    @Test
    public void mediumIdNotFoundTest() throws KartException {
        Category category = new Category(1, 1, "new");
        Medium medium = new Medium(category);
        Medium medium2 = new Medium(category);
        int catId = 1;
        int medId = 3;
        medium.setId(1);
        medium2.setId(2);
        Movie movie = new Movie("new");
        Movie movie2 = new Movie("new2");
        medium.addMovie(movie);
        medium2.addMovie(movie2);
        kartM.addCategory(category);
        kartM.addMedium(medium);
        kartM.addMedium(medium2);
        expected.expect(KartException.class);
        kartM.getMedium(catId, medId);
    }

    @Test
    public void mediumIdFoundTest() throws KartException {
        Category category = new Category(1, 1, "new");
        Medium medium = new Medium(category);
        int catId = 1;
        int medId = 1;
        medium.setId(medId);
        Movie movie = new Movie("novy");
        medium.addMovie(movie);
        kartM.addCategory(category);
        kartM.addMedium(medium);
        Medium result = kartM.getMedium(catId, medId);
        assertTrue(result.equals(medium));
    }

}
