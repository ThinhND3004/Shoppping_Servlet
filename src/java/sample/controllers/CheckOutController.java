/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.shopping.Cart;
import sample.shopping.ProductDAO;
import sample.user.UserDTO;

/**
 *
 * @author ACER
 */
public class CheckOutController extends HttpServlet {

    private static final String ERROR = "viewCart.jsp";
    private static final String SUCCESS = "ShoppingController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        String checkOutMessage = "";
        HttpSession session = request.getSession();
        
        try {
            
            ProductDAO dao = new ProductDAO();
            Cart cart = (Cart) session.getAttribute("CART");
            System.out.println("CART" + cart.getCart().toString());
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (cart.getCart().size() == 0) {
                checkOutMessage = "You do not have any products in your cart.";
            } else {
                boolean checkOutResult = dao.checkOut(cart, loginUser.getUserID());
                if (checkOutResult) {
                    url = SUCCESS;
                    checkOutMessage = "Checkout successfully!";
                    session.removeAttribute("CART"); // Clear the cart after successful checkout
                } else {
                    checkOutMessage = "Checkout failed due to insufficient stock.";
                }
            }
            request.setAttribute("CHECK_OUT_MESSAGE", checkOutMessage);
        } catch (Exception e) {
            log("ERROR at CheckOutController: " + e.toString());
        } finally {
            
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
