<%-- 
    Document   : login
    Created on : May 28, 2024, 8:57:28 AM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--<html>
    <head>
        <title>Login Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>Input your information:</h1>
        <form action="MainController" method="POST">
            User ID:<input type="text" name="userID" /></br>
            Password:<input type="password" name="password" /></br>
            <input type="submit" name="action" value="Login" />
            <input type="reset" value="Reset" />
        </form>

    </body>
</html>-->
        
        <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Optional: Add custom styles here */
        .form-container {
            max-width: 400px;
            margin: auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin-top: 50px;
        }
        .form-container h1 {
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="form-container">
            <h1>Login Page</h1>
            <form action="MainController" method="POST">
                <div class="form-group">
                    <label for="userID">User ID:</label>
                    <input type="text" class="form-control" id="userID" name="userID">
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" class="form-control" id="password" name="password">
                </div>
                <button type="submit" class="btn btn-primary" name="action" value="Login">Login</button>
                <button type="reset" class="btn btn-secondary">Reset</button>
            </form>
            
        ${requestScope.ERROR}
            <div style="margin-top: 10px;">
                <a href="MainController?action=Create_Page" class="btn btn-link">Create User</a>
                
            </div>
        </div>
    </div>

    <!-- Bootstrap JS and dependencies (optional) -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>
