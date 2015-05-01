package cz.muni.fi.pb138.odskart.backend;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

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
            Sheet sheet = spreadSheet.getSheet(i);

            list.add(new Category(i, sheet.getColumnCount() - 1, sheet.getName()));
        }
        return list;
    }

    @Override
    public void addCategory(String name, int maxMediumMovies) throws KartException {
        if (spreadSheet.getSheet(name, false) != null) {
            throw new KartException("Category with this name already exists!");
        }

        Sheet newSheet = spreadSheet.addSheet(name);
        newSheet.setColumnCount(maxMediumMovies);
        try {
            spreadSheet.saveAs(file);
        } catch (IOException ex) {
            throw new KartException("Unable to save file: " + ex.getMessage(), ex);
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
        try {
            spreadSheet.saveAs(file);
        } catch (IOException ex) {
            throw new KartException("Unable to save file: " + ex.getMessage(), ex);
        }
    }

    @Override
    public void addMedium(Category category, Medium medium) throws KartException {

        if (category == null) {
            throw new IllegalArgumentException("Category is null");
        }
        if (medium == null) {
            throw new IllegalArgumentException("Medium is null");
        }
        if (medium.getMovies().size() > category.getMaxMediumMovies()) {
            throw new IllegalArgumentException("Medium has more movies than category allows");
        }

        Sheet sheet = spreadSheet.getSheet(category.getId());

        // set new id
        Integer newIndex = sheet.getRowCount();
        sheet.setRowCount(newIndex + 1);
        sheet.getCellAt(0, newIndex).setValue(newIndex);
        medium.setId(newIndex);
        // add movies
        for (int j = 0; j < medium.getMovies().size(); j++) {

            String movieName = medium.getMovies().get(j).getName();
            sheet.getCellAt(j + 1, newIndex).setValue(movieName);
        }
        try {
            spreadSheet.saveAs(file);
        } catch (IOException ex) {
            throw new KartException("Unable to save file: " + ex.getMessage(), ex);
        }

    }

    @Override
    public void moveMedium(Category oldCat, Category newCat, Medium medium) throws KartException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeMedium(Category category, Medium medium) throws KartException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Medium> getCategoryMediums(Category category) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getMediumRowIndex(Medium medium) throws KartException {
        Sheet sheet = spreadSheet.getSheet(medium.getCategory().getId());
        for (int i = 1; i < sheet.getRowCount(); i++) {
            String sValue = sheet.getCellAt(0, i).getTextValue();
            if (Integer.parseInt(sValue) == medium.getId()) {
                return i;
            }
        }

        throw new KartException("Medium with ID " + medium.getId() + " was not found.");
    }

}
