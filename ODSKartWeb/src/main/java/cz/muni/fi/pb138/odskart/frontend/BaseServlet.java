package cz.muni.fi.pb138.odskart.frontend;

import cz.muni.fi.pb138.odskart.backend.KartManager;
import cz.muni.fi.pb138.odskart.backend.KartManagerImpl;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

/**
 * A class for managing the web application
 *
 * @author Ondřej Skýba
 */
public class BaseServlet extends HttpServlet {

    protected static final String ODS_PATH = "/kart.ods";
    protected KartManager manager;

    protected boolean prepareManager(HttpServletResponse response) throws IOException {
        File file = new File(getServletContext().getRealPath(ODS_PATH));
        try {
            manager = new KartManagerImpl(file);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(Categories.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(500, "Error accessing ODS file. Error message: " + ex.getMessage());
            return false;
        }
    }
}
