package cz.muni.fi.pb138.odskart.frontend;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Search", urlPatterns = {Search.SEARCH, Search.SEARCH_RESULTS})
public class Search extends BaseServlet {

    static final String SEARCH = "/Search";
    static final String SEARCH_RESULTS = "/SearchResults";
    private static final String SEARCH_JSP = "/jsp/search.jsp";
    private static final String SEARCH_RESULTS_JSP = "/jsp/searchResults.jsp";

    private void showSearchForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(SEARCH_JSP).forward(request, response);
    }

    private void search(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("mediums", manager.findMovie(request.getParameter("movie_name")));
        request.getRequestDispatcher(SEARCH_RESULTS_JSP).forward(request, response);
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
        showSearchForm(request, response);
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
            case SEARCH_RESULTS:
                search(request, response);
                break;
            default:
                throw new RuntimeException("Unknown operation: " + request.getServletPath());
        }
    }

    // </editor-fold>
}
