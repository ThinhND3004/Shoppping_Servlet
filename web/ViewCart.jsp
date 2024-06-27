<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import="sample.shopping.ProductDTO"%>
<%@page import="sample.shopping.Cart"%>
<%@page import="sample.shopping.Clothes"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MY STORE!!!</title>
        <!-- Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container mt-5">
            <div class="text-center mb-4">
                <h1>Your shopping cart!</h1>
            </div>
            <c:if test="${not empty sessionScope.CART}">
                <table class="table table-bordered table-striped">
                    <thead class="thead-dark">
                        <tr>
                            <th>NO</th>
                            <th>ID</th>
                            <th>NAME</th>
                            <th>QUANTITY</th>
                            <th>PRICE</th>
                            <th>EDIT</th>
                            <th>REMOVE</th>
                            <th>TOTAL</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="count" value="1" />
                        <c:set var="total" value="0" />
                        <c:forEach var="clothes" items="${sessionScope.CART.cart.values()}">
                            <c:set var="lineTotal" value="${clothes.price * clothes.quantity}" />
                            <c:set var="total" value="${total + lineTotal}" />
                            <form action="MainController" method="POST" class="form-inline">
                                <tr>
                                    <td><c:out value="${count}" /></td>
                                    <td><c:out value="${clothes.id}" /></td>
                                    <td><c:out value="${clothes.name}" /></td>
                                    <td>
                                        <input type="number" name="quantity" value="${clothes.quantity}" class="form-control" required>
                                    </td>
                                    <td><fmt:formatNumber value="${clothes.price}" type="currency" /></td>
                                    <td>
                                        <input type="submit" name="action" value="Edit" class="btn btn-primary">
                                        <input type="hidden" name="id" value="${clothes.id}">
                                    </td>
                                    <td>
                                        <a href="./MainController?action=Remove&id=${clothes.id}" class="btn btn-danger">Remove</a>
                                    </td>
                                    <td><fmt:formatNumber value="${lineTotal}" type="currency" /></td>
                                </tr>
                                <c:set var="count" value="${count + 1}" />
                            </form>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="text-right mb-4">
                    <h2>Total: <fmt:formatNumber value="${total}" type="currency" /></h2>
                </div>
                <div class="text-center">
                    <a href="MainController?action=Shopping_Product_Page" class="btn btn-info">Add More</a>
                    <a href="MainController?action=Check_out" class="btn btn-info">Check out</a>
                </div>
                
                <p>${requestScope.CHECK_OUT_MESSAGE}</p>
                
            </c:if>
        </div>
        <!-- Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
