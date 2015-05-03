/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb138.odskart.frontend;

import cz.muni.fi.pb138.odskart.backend.Category;
import cz.muni.fi.pb138.odskart.backend.KartException;
import cz.muni.fi.pb138.odskart.backend.KartManager;
import cz.muni.fi.pb138.odskart.backend.KartManagerImpl;
import cz.muni.fi.pb138.odskart.backend.Medium;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ThreeDots
 */
@WebServlet(name = "Category", urlPatterns = {CategoryDetail.CAT_DETAIL, CategoryDetail.MEDIUM_ADD, CategoryDetail.MEDIUM_DELETE})
public class CategoryDetail extends HttpServlet {

    static final String CAT_DETAIL = "/Category";
    static final String MEDIUM_ADD = "/AddMedium";
    static final String MEDIUM_DELETE = "/DeleteMedium";
    private static final String ODS_PATH = "/kart.ods";
    private static final String JSP_LIST = "/jsp/catDetail.jsp";
    private static final String JSP_ADD_MEDIUM = "/jsp/addMedium.jsp";

    private KartManager manager;

    private boolean prepareManager(HttpServletResponse response) throws IOException {
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

    private void showCategory(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        if (request.getParameter("id") != null) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                Category category = manager.getCategory(id);
                request.setAttribute("mediums", manager.getCategoryMediums(category));
                request.setAttribute("category", category);
                request.getRequestDispatcher(JSP_LIST).forward(request, response);
            } catch (KartException | NumberFormatException | NullPointerException ex) {
                Logger.getLogger(CategoryDetail.class.getName()).log(Level.SEVERE, null, ex);
                response.sendRedirect(request.getContextPath() + Categories.CAT_LIST);
            }
        } else {
            response.sendRedirect(request.getContextPath() + Categories.CAT_LIST);
        }
    }

    private void addMedium(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getMethod().equals("POST")) {
            AddMediumForm form;
            StringBuilder error = new StringBuilder();
            Medium medium = null;
            try {
                form = AddMediumForm.extractFromRequest(request, manager);
                medium = form.validateAndGet(error);
            } catch (KartException | NumberFormatException ex) {
                Logger.getLogger(CategoryDetail.class.getName()).log(Level.SEVERE, null, ex);
                error.append(ex.getMessage());
            }

            if (medium == null) {
                request.setAttribute("error", error.toString());
                request.getRequestDispatcher(JSP_ADD_MEDIUM).forward(request, response);
            } else {
                try {
                    manager.addMedium(medium);
                } catch (KartException ex) {
                    Logger.getLogger(Categories.class.getName()).log(Level.SEVERE, null, ex);
                    request.setAttribute("category", medium.getCategory());
                    request.setAttribute("error", ex.getMessage());
                    request.getRequestDispatcher(JSP_ADD_MEDIUM).forward(request, response);
                }
                response.sendRedirect(request.getContextPath() + CAT_DETAIL + "?id=" + medium.getCategory().getId());
            }
        } else {
            if (request.getParameter("id") != null) {
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    Category category = manager.getCategory(id);
                    request.setAttribute("category", category);
                    request.getRequestDispatcher(JSP_ADD_MEDIUM).forward(request, response);
                } catch (KartException | NumberFormatException | NullPointerException ex) {
                    Logger.getLogger(CategoryDetail.class.getName()).log(Level.SEVERE, null, ex);
                    response.sendRedirect(request.getContextPath() + Categories.CAT_LIST);
                }
            } else {
                response.sendRedirect(request.getContextPath() + Categories.CAT_LIST);
            }

        }
    }

    private void deleteMedium(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        if (request.getMethod().equals("POST")) {
            if (request.getParameter("medium_id") != null || request.getParameter("id") != null) {
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    int medium_id = Integer.parseInt(request.getParameter("medium_id"));

                    Category category = manager.getCategory(id);
                    Medium m = new Medium(category);
                    m.setId(medium_id);

                    manager.removeMedium(m);
                    response.sendRedirect(request.getContextPath() + CAT_DETAIL + "?id=" + id);
                } catch (KartException | NumberFormatException ex) {
                    Logger.getLogger(Categories.class.getName()).log(Level.SEVERE, null, ex);
                    request.setAttribute("error", ex.getMessage());
                    showCategory(request, response);
                }
            }
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
                addMedium(request, response);
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
                addMedium(request, response);
                break;
            case MEDIUM_DELETE:
                deleteMedium(request, response);
                break;
            default:
                showCategory(request, response);
        }
    }
    // </editor-fold>

}
