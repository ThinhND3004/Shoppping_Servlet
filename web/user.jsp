<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="sample.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Profile</title>
        <!-- Bootstrap CSS -->
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                background-color: #f8f9fa;
                font-family: 'Arial', sans-serif;
            }
            .profile-container {
                max-width: 600px;
                margin: 50px auto;
                background-color: #fff;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.1);
            }
            .profile-header {
                text-align: center;
                margin-bottom: 30px;
            }
            .profile-header h1 {
                font-size: 2em;
                margin-bottom: 10px;
            }
            .profile-header p {
                font-size: 1.2em;
                color: #666;
            }
            .profile-card {
                border: none;
                background-color: #f8f9fa;
            }
            .profile-card .card-body {
                padding: 20px;
            }
            .profile-card .card-body p {
                font-size: 1.1em;
                margin-bottom: 10px;
            }
            .shopping-button {
                display: block;
                width: 100%;
                margin-top: 20px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="profile-container">
                <div class="profile-header">
                    <h1>User Profile</h1>
                    <p>View and manage your personal information</p>
                </div>



                <div class="card profile-card">
                    <div class="card-body">
                        <h5 class="card-title">User Details</h5>
                        <p><strong>User ID:</strong> ${sessionScope.LOGIN_USER.userID}</p>
                        <p><strong>Password:</strong> ${sessionScope.LOGIN_USER.password}</p>
                        <p><strong>Full Name:</strong> ${sessionScope.LOGIN_USER.fullName}</p>
                        <p><strong>Role ID:</strong> ${sessionScope.LOGIN_USER.roleID}</p>
                    </div>
                </div>

                <c:url var="startShopping" value="MainController">
                    <c:param name="action" value="Shopping_Product_Page"></c:param>
                </c:url>   

                <c:url var="logout" value="MainController">
                    <c:param name="action" value="Logout"></c:param>
                </c:url>  

                <a href="${startShopping}" class="btn btn-primary shopping-button">Start Shopping</a>
                <a href="${logout}" class="btn btn-danger mt-3">Logout</a>
            </div>
        </div>

        <!-- Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
