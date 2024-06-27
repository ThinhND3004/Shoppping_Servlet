<%-- 
    Document   : create
    Created on : Jun 11, 2024, 7:45:43 AM
    Author     : ACER
--%>

<%@page import="sample.user.UserError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Create User</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Bootstrap CSS -->
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container mt-5">
            <h1 class="mb-4">Input User Information</h1>
            <form action="MainController" method="POST">
                <div class="form-group">
                    <label for="userID">UserID:</label>
                    <input type="text" class="form-control" id="userID" name="userID" required="">
                    <small class="text-danger">${requestScope.userError.getUserIdError}</small>
                </div>
                <div class="form-group">
                    <label for="fullName">Full Name:</label>
                    <input type="text" class="form-control" id="fullName" name="fullName" required="">
                    <small class="text-danger">${requestScope.userError.getFullNameError}</small>
                </div>
                <div class="form-group">
                    <label for="roleID">RoleID:</label>
                    <input type="text" class="form-control" id="roleID" name="roleID" value="US" readonly="">
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" class="form-control" id="password" name="password" required="">
                </div>
                <div class="form-group">
                    <label for="confirm">Confirm Password:</label>
                    <input type="password" class="form-control" id="confirm" name="confirm" required="">
                    <small class="text-danger">${requestScope.userError.getConfirmError}</small>
                </div>
                <button type="submit" class="btn btn-primary" name="action" value="Create">Create</button>
                <button type="reset" class="btn btn-secondary">Reset</button>
            </form>
            <div class="text-danger mt-3">${requestScope.userError.getError}</div>
        </div>

        <!-- Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
