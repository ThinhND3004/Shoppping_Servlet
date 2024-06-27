/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.shopping;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import sample.user.UserDTO;
import sample.utils.DBUtils;

/**
 *
 * @author ACER
 */
public class ProductDAO {
     private static final String GET_ALL_PRODUCT = "SELECT p.id, name, quantity, pp.price AS price  FROM Product p JOIN ProductPrice pp ON p.id = pp.id WHERE p.status = 1 AND pp.status = 1";
     private static final String GET_PRODUCT_BY_ID = "SELECT p.id, name, quantity, pp.price AS price  FROM Product p JOIN ProductPrice pp ON p.id = pp.id WHERE p.id = ? AND p.status = 1 AND pp.status = 1";
     private static final String GET_PRODUCT_QUANTITY_BY_ID = "SELECT quantity FROM Product WHERE id = ?";
    private static final String CREATE_ORDER = "INSERT INTO Orders (id, userId, date, total, status) VALUES (?, ?, ?, ?, ?)";
    private static final String CREATE_ORDER_DETAIL = "INSERT INTO OrderDetail (orderId, productId, productPriceId, quantity, status) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_PRODUCT_QUANTITY = "UPDATE Product SET quantity = ? WHERE id = ?";

     public List<ProductDTO> getAllProduct() throws SQLException, ClassNotFoundException {
        ProductDTO product = null;
        List<ProductDTO> products = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL_PRODUCT);
                rs = ptm.executeQuery();
                while (rs.next()) {

                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    int quantity = Integer.parseInt(rs.getString("quantity"));
                    double price = Double.parseDouble(rs.getString("price"));

                    product = new ProductDTO(id, name, quantity, price);
                    products.add(product);
                }
            }
        }  finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return products;
    }



     public ProductDTO getProductById(String id) throws SQLException, ClassNotFoundException {
        ProductDTO product = null;

        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_PRODUCT_BY_ID);
                ptm.setString(1, id);
                rs = ptm.executeQuery();
                if (rs.next()) {

                    String idProduct = rs.getString("id");
                    String name = rs.getString("name");
                    double price = Double.parseDouble(rs.getString("price"));

                    product = new ProductDTO(idProduct, name, 0, price);
                }
            }
        }  finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return product;
    }

    public boolean checkOut(Cart cart, String userId) throws SQLException, ClassNotFoundException {
        boolean checkOut = false;
        Connection conn = null;
        PreparedStatement ptmOrder = null;
        PreparedStatement ptmOrderDetail = null;
        PreparedStatement ptmUpdateProduct = null;
        PreparedStatement ptmCheckQuantity = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                conn.setAutoCommit(false); // Start transaction

                // Check if product quantities are sufficient
                for (ProductDTO product : cart.getCart().values()) {
                    ptmCheckQuantity = conn.prepareStatement(GET_PRODUCT_QUANTITY_BY_ID);
                    
                    ptmCheckQuantity.setInt(1, Integer.parseInt(product.getId()));
                    rs = ptmCheckQuantity.executeQuery();
                    if (rs.next()) {
                        int availableQuantity = rs.getInt("quantity");
                        System.out.println("SO LUONG CUA " + product.getName() + ": " + availableQuantity);
                        System.out.println("SO LUONG MUA " + product.getQuantity());
                        if (availableQuantity < product.getQuantity() || availableQuantity < 0) {
                            conn.rollback(); // Rollback transaction
                            return false;
                        }
                    } else {
                        conn.rollback(); // Rollback transaction
                        return false;
                    }
                }

                // Generate order ID (e.g., based on timestamp)
                UUID uuid = UUID.randomUUID();
        // Hash UUID to int
        int orderId = uuid.hashCode();
                System.out.println("orderid " + orderId);
                Date orderDate = new Date(System.currentTimeMillis());
                System.out.println("date " + orderDate);
                double total = 0;
                for (ProductDTO product : cart.getCart().values()) {
                    total += product.getPrice() * product.getQuantity();
                }

                // Insert order into Orders table
                ptmOrder = conn.prepareStatement(CREATE_ORDER);
                ptmOrder.setLong(1, orderId);
                ptmOrder.setString(2, userId);
                ptmOrder.setDate(3, orderDate);
                ptmOrder.setDouble(4, total);
                ptmOrder.setBoolean(5, true); // Assuming 'true' indicates active status
                ptmOrder.executeUpdate();

                // Insert order details into OrderDetail table
                ptmOrderDetail = conn.prepareStatement(CREATE_ORDER_DETAIL);
                ptmUpdateProduct = conn.prepareStatement(UPDATE_PRODUCT_QUANTITY);
                for (ProductDTO product : cart.getCart().values()) {
                    ptmOrderDetail.setLong(1, orderId);
                    ptmOrderDetail.setInt(2, Integer.parseInt(product.getId()));
                    ptmOrderDetail.setInt(3, Integer.parseInt(product.getId())); // Assuming productPriceId is same as productId
                    ptmOrderDetail.setInt(4, product.getQuantity());
                    ptmOrderDetail.setBoolean(5, true);
                    ptmOrderDetail.executeUpdate();

                    // Update product quantity in Product table
                    int newQuantity = rs.getInt("quantity") - product.getQuantity();
                    if (newQuantity < 0) {
                        conn.rollback(); // Rollback transaction
                        return false;
                    }
                    ptmUpdateProduct.setInt(1, newQuantity);
                    ptmUpdateProduct.setInt(2, Integer.parseInt(product.getId()));
                    ptmUpdateProduct.executeUpdate();
                }

                conn.commit(); // Commit transaction
                checkOut = true;
            }
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback(); // Rollback transaction on error
            }
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptmOrder != null) {
                ptmOrder.close();
            }
            if (ptmOrderDetail != null) {
                ptmOrderDetail.close();
            }
            if (ptmUpdateProduct != null) {
                ptmUpdateProduct.close();
            }
            if (ptmCheckQuantity != null) {
                ptmCheckQuantity.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return checkOut;
    }
}
