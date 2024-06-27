<%-- 
    Document   : shopping
    Created on : Jun 14, 2024, 7:59:10 AM
    Author     : ACER
--%>

<%@page import="sample.shopping.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>FPT FASHION SHOP</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container mt-5">
            <%
                List<ProductDTO> products = (List<ProductDTO>) request.getAttribute("PRODUCTS");
                if (products != null) {
            %>
            <div class="text-center mb-4">
                <h1>Welcome to my store!</h1>
            </div>
            <table class="table table-bordered table-striped">
                <thead class="thead-dark">
                    <tr>
                        <th>NO</th>
                        <th>Product ID</th>
                        <th>Product Name</th>
                        <th>Product Price</th>
                        <th>Quantity</th>
                        <th>Add</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int count = 1;
                        for (ProductDTO dto : products) {
                    %>
                    <form action="MainController" method="POST" class="form-inline">
                        <tr>
                            <td><%= count++%></td>
                            <td><%= dto.getId()%></td>
                            <td><%= dto.getName()%></td>
                            <td><%= dto.getPrice()%></td>
                            <td>
                                <input type="number" name="quantity" class="form-control" min="1" required>
                            </td>
                            <td>
                                <input type="submit" name="action" value="Add" class="btn btn-primary">
                                <input type="hidden" name="id" value="<%=dto.getId()%>">
                            </td>
                        </tr>
                    </form>
                    <%
                        }
                    %>
                </tbody>
            </table>
            <div class="text-center">
                <a href="MainController?action=View" class="btn btn-info">View Cart</a>
            </div>
            <div class="text-center mt-3">
                <p class="text-danger">${requestScope.ADD_CART_MESSAGE}</p>
                <p class="text-danger">${requestScope.CHECK_OUT_MESSAGE}</p>
            </div>
            <%
                }
            %>
        </div>
        <!-- Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
