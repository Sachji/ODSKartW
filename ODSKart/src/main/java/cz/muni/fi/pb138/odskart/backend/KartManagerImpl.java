package cz.muni.fi.pb138.odskart.backend;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

/**
 * An implementation class for KartManager interface
 *
 * @author Jiří Šácha, Ondřej Skýba
 */
public class KartManagerImpl implements KartManager {

    private final File file;
    private final SpreadSheet spreadSheet;

    public KartManagerImpl(File file) throws IOException {
        this.file = file;
        spreadSheet = SpreadSheet.createFromFile(file);
    }

    @Override
    public List<Category> getCategoryList() {
        List<Category> list = new ArrayList<>();
        for (int i = 0; i < spreadSheet.getSheetCount(); i++) {
            try {
                list.add(getCategory(i));
            } catch (KartException ex) {
                Logger.getLogger(KartManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public Category getCategory(int id) throws KartException {
        if (id >= spreadSheet.getSheetCount()) {
            throw new KartException("Category with this ID doesn't exist");
        }
        Sheet sheet = spreadSheet.getSheet(id);
        return new Category(id, sheet.getColumnCount() - 1, sheet.getName());
    }

    @Override
    public void addCategory(Category category) throws KartException {
        if (spreadSheet.getSheet(category.getName(), false) != null) {
            throw new KartException("Category with this name already exists!");
        }

        Sheet newSheet = spreadSheet.addSheet(category.getName());
        newSheet.setColumnCount(category.getMaxMediumMovies() + 1);
        newSheet.getCellAt(0, 0).setValue("ID");
        for (int i = 0; i < category.getMaxMediumMovies(); i++) {
            newSheet.getCellAt(i + 1, 0).setValue("Movie #" + Integer.toString(i + 1));
        }
    }

    @Override
    public void removeCategory(int id) throws KartException {
        if (id >= spreadSheet.getSheetCount()) {
            throw new KartException("Category doesn't exist!");
        }
        // every spreedsheet need to have at least one sheet(= category) 
        if (spreadSheet.getSheetCount() == 1) {
            throw new KartException("Removing last category");
        }
        Sheet toRemove = spreadSheet.getSheet(id);
        toRemove.detach();
    }

    @Override
    public void addMedium(Medium medium) throws KartException {
        if (medium == null) {
            throw new IllegalArgumentException("Medium is null");
        }
        if (medium.getCategory() == null) {
            throw new IllegalArgumentException("Medium category is null");
        }
        if (medium.getMovies().size() > medium.getCategory().getMaxMediumMovies()) {
            throw new KartException("Medium has more movies than category allows");
        }
        if (medium.getMovies().isEmpty()) {
            throw new KartException("Medium has to have at least one movie");
        }
        Sheet sheet = spreadSheet.getSheet(medium.getCategory().getId());

        // set new id
        Integer rowIndex = sheet.getRowCount();
        sheet.setRowCount(rowIndex + 1);

        Integer newId;

        if (rowIndex > 1) {
            BigDecimal pom = (BigDecimal) sheet.getImmutableCellAt(0, rowIndex - 1).getValue();
            newId = pom.intValueExact() + 1;
        } else {
            newId = 1;
        }
        sheet.getCellAt(0, rowIndex).setValue(newId);
        medium.setId(newId);
        // add movies
        for (int j = 0; j < medium.getMovies().size(); j++) {

            String movieName = medium.getMovies().get(j).getName();
            sheet.getCellAt(j + 1, rowIndex).setValue(movieName);
        }
    }

    @Override
    public void moveMedium(Category newCat, Medium medium) throws KartException {
        removeMedium(medium);
        medium.setCategory(newCat);
        addMedium(medium);
    }

    @Override
    public void removeMedium(Medium medium) throws KartException {

        Sheet sheet = spreadSheet.getSheet(medium.getCategory().getId());
        sheet.removeRow(getMediumRowIndex(medium));
    }

    @Override
    public List<Medium> getCategoryMediums(Category category) {

        Sheet sheet = spreadSheet.getSheet(category.getId());
        List<Medium> mediumList = new ArrayList<>();

        for (int i = 1; i < sheet.getRowCount(); i++) {

            Medium m = new Medium(category);
            BigDecimal pom = (BigDecimal) sheet.getImmutableCellAt(0, i).getValue();
            m.setId(pom.intValueExact());

            for (int j = 0; j < category.getMaxMediumMovies(); j++) {

                String movieName = sheet.getImmutableCellAt(j + 1, i).getTextValue();
                if (!movieName.equals("")) {
                    m.addMovie(new Movie(movieName));
                }

            }
            mediumList.add(m);

        }
        return mediumList;
    }

    @Override
    public List<Medium> findMovie(String movieName) {

        List<Category> categories = getCategoryList();
        List<Medium> movMediums = new ArrayList<>();

        for (Category cat : categories) {
            List<Medium> catMediums = getCategoryMediums(cat);

            for (Medium med : catMediums) {
                if (med.getMovie(movieName) != -1) {
                    movMediums.add(med);
                }
            }
        }
        return movMediums;
    }

    @Override
    public Medium getMedium(int categoryID, int mediumID) throws KartException {

        Category cat = getCategory(categoryID);
        List<Medium> mediums = getCategoryMediums(cat);

        for (Medium m : mediums) {
            if (m.getId() == mediumID) {
                return m;
            }
        }
        throw new KartException("Medium with given ID not found");
    }

    private int getMediumRowIndex(Medium medium) throws KartException {
        Sheet sheet = spreadSheet.getSheet(medium.getCategory().getId());
        for (int i = 1; i < sheet.getRowCount(); i++) {
            String sValue = sheet.getImmutableCellAt(0, i).getTextValue();
            if (Integer.parseInt(sValue) == medium.getId()) {
                return i;
            }
        }

        throw new KartException("Medium with ID " + medium.getId() + " was not found.");
    }

    public void saveFile() throws KartException {

        try {
            spreadSheet.saveAs(file);
        } catch (IOException ex) {
            throw new KartException("Unable to save file: " + ex.getMessage(), ex);
        }
    }

}
