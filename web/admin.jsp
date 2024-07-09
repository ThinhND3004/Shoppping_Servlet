<%-- 
    Document   : admin
    Created on : May 28, 2024, 9:03:14 AM
    Author     : ACER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.List"%>
<%@page import="sample.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <!-- Bootstrap CSS -->
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container mt-5">
            <c:if test="${sessionScope.LOGIN_USER == null || sessionScope.LOGIN_USER.roleID ne 'AD'}">
                <c:redirect url="login.jsp"></c:redirect>
            </c:if>

            <h1 class="mb-4">WELCOME ${sessionScope.LOGIN_USER.fullName}</h1>
            <c:url var="logoutLink" value="MainController">
                <c:param name="action" value="Logout"></c:param>
            </c:url>
            <a href="${logoutLink}" class="btn btn-danger mb-3">Logout </a>

            <form action="MainController" method="POST" class="form-inline mb-4">
                <div class="form-group mr-2">
                    <label for="search" class="sr-only">Search:</label>
                    <input type="text" id="search" name="search" class="form-control" value="${sessionScope.SEARCH_VALUE}">
                </div>
                <button type="submit" name="action" value="Search" class="btn btn-primary">Search</button>
            </form>

            <c:if test="${requestScope.LIST_USER != null}">
                <c:if test="${not empty requestScope.LIST_USER}">
                    <table class="table table-bordered">
                        <thead class="thead-dark">
                            <tr>
                                <th>NO</th>
                                <th>UserID</th>
                                <th>Full Name</th>
                                <th>RoleID</th>
                                <th>Password</th>
                                <th>Update</th>
                                <th>Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="user" varStatus="counter" items="${requestScope.LIST_USER}">
                                <tr>
                            <form action="MainController" method="POST">
                                <td>${counter.count}</td>
                                <td>
                                    <input type="text" name="userID" class="form-control" value="${user.userID}">
                                </td>
                                <td>
                                    <input type="text" name="fullName" class="form-control" value="${user.fullName}" required="">
                                </td>
                                <td>
                                    <input type="text" name="roleID" class="form-control" value="${user.roleID}" required="">
                                </td>
                                <td>
                                    ${user.password}
                                </td>
                                <td>
                                    <input type="submit" name="action" value="Update" class="btn btn-warning"> 
                                    <input type="hidden" name="search" value="${param.search}">
                                </td>
                                <td>
                                    <c:url var="deleteLink" value="MainController">
                                        <c:param name="action" value="Delete"></c:param>
                                        <c:param name="userID" value="${user.userID}"></c:param>
                                        <c:param name="search" value="${sessionScope.SEARCH_VALUE}"></c:param>
                                    </c:url>
                                    <a href="${deleteLink}" class="btn btn-danger">Delete</a>
                                </td>
                            </form>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                    <c:if test="${not empty requestScope.DELETE_USER_MESSAGE}">

                        <div class="alert alert-info">
                            ${requestScope.DELETE_USER_MESSAGE}
                        </div>
                    </c:if>


                </c:if>
            </c:if>
        </div>

        <!-- Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
