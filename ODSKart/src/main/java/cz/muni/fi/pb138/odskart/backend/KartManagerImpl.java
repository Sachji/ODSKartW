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
            list.add(new Category(i, sheet.getColumnCount(), sheet.getName()));
        }
        return list;
    }

    @Override
    public void addCategory(String name, int maxMediumMovies) throws KartException, IOException {
        if (spreadSheet.getSheet(name, false) != null) {
            throw new KartException("Category with this name already exists!");
        }

        Sheet newSheet = spreadSheet.addSheet(name);
        newSheet.setColumnCount(maxMediumMovies);
        spreadSheet.saveAs(file);
    }

    @Override
    public void removeCategory(int id) throws KartException, IOException {
      
        if (id >= spreadSheet.getSheetCount()) {
            throw new KartException("Category doesn't exist!");
        }
        // every spreedsheet need to have at least one sheet(= category) 
        if (spreadSheet.getSheetCount() == 1){
            throw new KartException("Removing last category");
        }
        System.out.println(spreadSheet.getSheetCount()+ "pica" + id);
        Sheet toRemove = spreadSheet.getSheet(id);
        toRemove.detach();
        spreadSheet.saveAs(file);
    }

}
