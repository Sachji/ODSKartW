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
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Categories", urlPatterns = {Categories.CAT_LIST, Categories.CAT_DELETE, Categories.CAT_ADD})
public class Categories extends HttpServlet {

    static final String CAT_LIST = "/Categories";
    static final String CAT_DELETE = "/DeleteCategory";
    static final String CAT_ADD = "/AddCategory";
    private static final String ODS_PATH = "/kart.ods";
    private static final String JSP_LIST = "/WEB-INF/jsp/categories.jsp";
    private static final String JSP_FORM = "/WEB-INF/jsp/addCategory.jsp";

    private KartManager manager;

    private void prepareManager() {
        File file = new File(getServletContext().getRealPath(ODS_PATH));
        try {
            manager = new KartManagerImpl(file);
        } catch (IOException ex) {
            Logger.getLogger(Categories.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void categoryList(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        File file = new File(getServletContext().getRealPath(ODS_PATH));
        try {
            manager = new KartManagerImpl(file);
        } catch (IOException ex) {
            Logger.getLogger(Categories.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("categories", manager.getCategoryList());
        request.getRequestDispatcher(JSP_LIST).forward(request, response);
    }

    private void addCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        prepareManager();
        if (request.getMethod().equals("POST")) {
            if (request.getParameter("cancel") != null) {
                response.sendRedirect(request.getContextPath() + CAT_LIST);
                return;
            }

            AddCategoryForm form = AddCategoryForm.extractFromRequest(request);
            StringBuilder error = new StringBuilder();
            Category category = form.validateAndGet(error);

            if (category == null) {
                request.setAttribute("error", error.toString());
                request.getRequestDispatcher(JSP_FORM).forward(request, response);
            } else {
                try {
                    manager.addCategory(category);
                } catch (KartException ex) {
                    Logger.getLogger(Categories.class.getName()).log(Level.SEVERE, null, ex);
                    request.setAttribute("error", ex.getMessage());
                    request.getRequestDispatcher(JSP_FORM).forward(request, response);
                }
                response.sendRedirect(request.getContextPath() + CAT_LIST);
            }
        } else {
            request.getRequestDispatcher(JSP_FORM).forward(request, response);
        }
    }

    private void deleteCategory(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        if (request.getMethod().equals("POST")) {
            try {
                File file = new File(getServletContext().getRealPath(ODS_PATH));
                manager = new KartManagerImpl(file);
                manager.removeCategory(Integer.parseInt(request.getParameter("id")));
                response.sendRedirect(request.getContextPath() + "/Categories");
            } catch (KartException ex) {
                Logger.getLogger(Categories.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("error", ex.getMessage());

                categoryList(request, response);
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
        String action = request.getServletPath();
        switch (action) {
            case CAT_ADD:
                addCategory(request, response);
                break;
            default:
                categoryList(request, response);
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
        String action = request.getServletPath();
        switch (action) {
            case CAT_DELETE:
                deleteCategory(request, response);
                break;
            case CAT_ADD:
                addCategory(request, response);
                break;
            default:
                throw new RuntimeException("Unknown operation: " + request.getServletPath());
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
