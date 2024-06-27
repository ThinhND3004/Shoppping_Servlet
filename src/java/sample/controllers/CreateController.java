/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.user.UserDAO;
import sample.user.UserDTO;
import sample.user.UserError;

/**
 *
 * @author ACER
 */
public class CreateController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private static final String ERROR = "create.jsp";
    private static final String SUCCESS = "login.html";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        UserError userError = new UserError();
        
        try {
            boolean checkValidation = true;
            UserDAO aO = new UserDAO();
            
            String userID = request.getParameter("userID");
            String fullName = request.getParameter("fullName");
            String roleID = request.getParameter("roleID"); //khong dc hard code
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            
            //validation
            
            if(userID.length() < 2 || userID.length() > 10)
            {
                checkValidation = false;
                userError.setUserIdError("UserID must be in (2, 10)");
            }
            
//            boolean checkDuplicate = aO.checkDuplicate(userID);
//            if(checkDuplicate)
//            {
//                checkValidation = false;
//                userError.setUserIdError("UserID has been dup");
//            }
            
            if(fullName.length() < 5 || userID.length() > 50)
            {
                checkValidation = false;
                userError.setUserIdError("FullName must be in (5, 50)");
            }
            if(!confirm.equals(password) )
            {
                checkValidation = false;
                userError.setUserIdError("2 pass k giong nhau");
            }
            if(checkValidation)
            {
                UserDTO user = new UserDTO(userID, fullName, roleID, password);
                boolean checkInsert = aO.insert(user);
                if(checkInsert)
                {
                    url = SUCCESS;
                } else
                {
                    userError.setError("Unknow Error");
                    request.setAttribute("USER_ERROR", userError);
                }
            } else
            {
                request.setAttribute("USER_ERROR", userError);
            }
        } catch (Exception e) {
            log("ERROR At CreateController : " + e.toString());
            if(e.toString().contains("duplicate"))
            {
                userError.setUserIdError("Trung UserID roi!!");
                request.setAttribute("USER_ERROR", userError);
            }
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
