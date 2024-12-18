/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.user.UserDAO;
import sample.user.UserDTO;

/**
 *
 * @author ACER
 */
public class LoginController extends HttpServlet {

    private static final String ERROR = "login.jsp";
    private static final String US = "US";
    private static final String AD = "AD";
    private static final String ADMIN_PAGE = "admin.jsp";
    private static final String USER_PAGE = "user.jsp";

    private static final String ERROR_MESSAGE = "Your role is not support yet!";
    private static final String INCORRECT_MESSAGE = "Incorrect UserID or Password!";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;

 

        try {
            String userID = request.getParameter("userID");
            String password = request.getParameter("password");
            String rememberMe = request.getParameter("rememberMe");

            UserDAO dao = new UserDAO();
            UserDTO dto = dao.checkLogin(userID, password);
            //xác thực
            if (dto != null) {
                //phân quyền
                HttpSession session = request.getSession();
                session.setAttribute("LOGIN_USER", dto);
                String roleID = dto.getRoleID();
                if (AD.equals(roleID)) {
                    url = ADMIN_PAGE;
                } else if (US.equals(roleID)) {
                    url = USER_PAGE;
                } else {
                    request.setAttribute("ERROR", ERROR_MESSAGE);
                }
                if ("on".equals(rememberMe)) {
                    Cookie userIDCookie = new Cookie("userID", userID);
                    Cookie passwordCookie = new Cookie("password", password);
                    userIDCookie.setMaxAge(30 * 60);
                    passwordCookie.setMaxAge(30 * 60);
                    response.addCookie(userIDCookie);
                    response.addCookie(passwordCookie);
                } else {
                    // Clear cookies if "Remember Me" is not checked
                    Cookie userIDCookie = new Cookie("userID", "");
                    Cookie passwordCookie = new Cookie("password", "");
                    userIDCookie.setMaxAge(0);
                    passwordCookie.setMaxAge(0);
                    response.addCookie(userIDCookie);
                    response.addCookie(passwordCookie);
                }
            } else {
                request.setAttribute("ERROR", INCORRECT_MESSAGE);
            }

        } catch (Exception e) {
            log("Error at LoginServlet: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
