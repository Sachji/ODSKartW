package cz.muni.fi.pb138.odskart.frontend;

import cz.muni.fi.pb138.odskart.backend.Category;
import cz.muni.fi.pb138.odskart.backend.KartException;
import cz.muni.fi.pb138.odskart.backend.Medium;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A class detailing categories and their functions
 *
 * @author Ondřej Skýba
 */
@WebServlet(name = "Category", urlPatterns = {CategoryDetail.CAT_DETAIL, CategoryDetail.MEDIUM_ADD, CategoryDetail.MEDIUM_DELETE, CategoryDetail.MEDIUM_MOVE})
public class CategoryDetail extends BaseServlet {

    static final String CAT_DETAIL = "/Category";
    static final String MEDIUM_ADD = "/AddMedium";
    static final String MEDIUM_DELETE = "/DeleteMedium";
    static final String MEDIUM_MOVE = "/MoveMedium";
    private static final String JSP_LIST = "/jsp/catDetail.jsp";
    private static final String JSP_ADD_MEDIUM = "/jsp/addMedium.jsp";
    private static final String JSP_MOVE_MEDIUM = "/jsp/moveMedium.jsp";

    private Category tryGetCategory(HttpServletRequest request, HttpServletResponse response, String param)
            throws IOException {
        if (request.getParameter(param) != null) {
            try {
                int id = Integer.parseInt(request.getParameter(param));
                Category category = manager.getCategory(id);
                return category;
            } catch (KartException | NumberFormatException | NullPointerException ex) {
                Logger.getLogger(CategoryDetail.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("error", ex.getMessage());
            }
        } else {
            response.sendRedirect(request.getContextPath() + Categories.CAT_LIST);
        }
        return null;
    }

    private Medium tryGetMedium(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        if (request.getParameter("medium_id") != null && request.getParameter("id") != null) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                int medium_id = Integer.parseInt(request.getParameter("medium_id"));

                Medium m = manager.getMedium(id, medium_id);
                return m;
            } catch (KartException | NumberFormatException ex) {
                Logger.getLogger(Categories.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("error", ex.getMessage());
            }
        } else {
            response.sendRedirect(request.getContextPath() + Categories.CAT_LIST);
        }
        return null;
    }

    private void showCategory(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Category category;
        if ((category = tryGetCategory(request, response, "id")) == null) {
            response.sendRedirect(request.getContextPath() + Categories.CAT_LIST);
            return;
        }
        request.setAttribute("mediums", manager.getCategoryMediums(category));
        request.setAttribute("category", category);
        request.getRequestDispatcher(JSP_LIST).forward(request, response);
    }

    private void addMediumPOST(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        AddMediumForm form;
        StringBuilder error = new StringBuilder();
        Medium medium = null;
        try {
            form = AddMediumForm.extractFromRequest(request, manager);
            medium = form.validateAndGet(error);

        } catch (KartException | NumberFormatException ex) {
            Logger.getLogger(CategoryDetail.class
                    .getName()).log(Level.SEVERE, null, ex);
            error.append(ex.getMessage());
        }

        if (medium == null) {
            request.setAttribute("error", error.toString());
            request.getRequestDispatcher(JSP_ADD_MEDIUM).forward(request, response);
        } else {
            try {
                manager.addMedium(medium);
                manager.saveFile();

            } catch (KartException ex) {
                Logger.getLogger(Categories.class
                        .getName()).log(Level.SEVERE, null, ex);
                request.setAttribute(
                        "category", medium.getCategory());
                request.setAttribute(
                        "error", ex.getMessage());
                request.getRequestDispatcher(JSP_ADD_MEDIUM)
                        .forward(request, response);
            }
            response.sendRedirect(request.getContextPath() + CAT_DETAIL + "?id=" + medium.getCategory().getId());
        }
    }

    private void addMediumGET(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Category category;
        if ((category = tryGetCategory(request, response, "id")) == null) {
            response.sendRedirect(request.getContextPath() + Categories.CAT_LIST);
            return;
        }
        request.setAttribute("category", category);
        request.getRequestDispatcher(JSP_ADD_MEDIUM).forward(request, response);
    }

    private void deleteMedium(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Medium medium;
        if ((medium = tryGetMedium(request, response)) == null) {
            showCategory(request, response);
            return;
        }
        try {
            manager.removeMedium(medium);
            manager.saveFile();
        } catch (KartException ex) {
            Logger.getLogger(CategoryDetail.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("error", ex.getMessage());
        }
        response.sendRedirect(request.getContextPath() + CAT_DETAIL + "?id=" + medium.getCategory().getId());

    }

    private void moveMediumGET(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Medium medium;
        if ((medium = tryGetMedium(request, response)) == null) {
            return;
        }
        request.setAttribute("medium", medium);
        request.setAttribute("categories", manager.getCategoryList());
        request.getRequestDispatcher(JSP_MOVE_MEDIUM).forward(request, response);
    }

    private void moveMediumPOST(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Medium medium;
        Category destCategory;
        if ((medium = tryGetMedium(request, response)) == null || (destCategory = tryGetCategory(request, response, "dest_category")) == null) {
            request.getRequestDispatcher(JSP_MOVE_MEDIUM).forward(request, response);
            return;
        }
        Medium initialMedium = new Medium(medium);
        try {
            manager.moveMedium(destCategory, medium);
            manager.saveFile();
            response.sendRedirect(request.getContextPath() + CAT_DETAIL + "?id=" + destCategory.getId());
        } catch (KartException ex) {
            Logger.getLogger(CategoryDetail.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("error", ex.getMessage());
            request.setAttribute("medium", initialMedium);
            request.setAttribute("categories", manager.getCategoryList());
            request.getRequestDispatcher(JSP_MOVE_MEDIUM).forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!prepareManager(response)) {
            return;
        }
        String action = request.getServletPath();
        switch (action) {
            case MEDIUM_ADD:
                addMediumGET(request, response);
                break;
            case MEDIUM_MOVE:
                moveMediumGET(request, response);
                break;
            default:
                showCategory(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!prepareManager(response)) {
            return;
        }
        String action = request.getServletPath();
        switch (action) {
            case MEDIUM_ADD:
                addMediumPOST(request, response);
                break;
            case MEDIUM_DELETE:
                deleteMedium(request, response);
                break;
            case MEDIUM_MOVE:
                moveMediumPOST(request, response);
                break;
            default:
                throw new RuntimeException("Unknown operation: " + request.getServletPath());
        }
    }
    // </editor-fold>

}
