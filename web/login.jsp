<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login Page</title>
        <!-- Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            /* Custom styles for the login form */
            body {
                background: url('https://images.unsplash.com/photo-1512436991641-6745cdb1723f?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNjUyOXwwfDF8c2VhcmNofDN8fHNoa3VwcGluZyUyMHNpdGV8ZW58MHx8fHwxNjI1ODQ4NzQ0&ixlib=rb-1.2.1&q=80&w=1080') no-repeat center center fixed;
                background-size: cover;
                font-family: 'Arial', sans-serif;
            }
            .form-container {
                max-width: 400px;
                margin: auto;
                padding: 30px;
                border-radius: 10px;
                background-color: rgba(255, 255, 255, 0.9);
                box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.3);
                margin-top: 150px;
            }
            .form-container h1 {
                text-align: center;
                margin-bottom: 20px;
                font-weight: bold;
            }
            .form-group label {
                font-weight: bold;
            }
            .btn-primary, .btn-secondary {
                width: 100%;
            }
            .btn-link {
                display: block;
                text-align: center;
                margin-top: 10px;
            }
            .error-message {
                color: red;
                text-align: center;
                margin-top: 10px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="form-container">
                <h1>Login</h1>
                <c:set var="cookie" value="${pageContext.cookies}">
                <form id="login-form" action="MainController" method="POST">
                    <div class="form-group">
                        <label for="userID">User ID</label>
                        <input type="text" class="form-control" id="userID" name="userID" value="${cookie.userID}" required>
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" id="password" name="password" value="${cookie.password}" required>
                    </div>
                    <div class="form-group form-check">
                        <input type="checkbox" class="form-check-input" id="rememberMe" name="rememberMe">
                        <label class="form-check-label" for="rememberMe">Remember Me</label>
                    </div>
                    <div class="g-recaptcha" data-sitekey="6LcNiwIqAAAAAKRVj_Ydpof98oIQfTY0NuPpLalk"></div>
                    <div id="errorCapcha" style="color: red"></div>
                    <button type="submit" class="btn btn-primary mb-3 mt-3" name="action" value="Login">Login</button>
                    <button type="reset" class="btn btn-secondary">Reset</button>
                </form>
                <div style="color: red">
                    
                    ${requestScope.ERROR}
                    
                </div>
                
                <a href="MainController?action=Create_Page" class="btn btn-link">Create User</a>
            </div>
        </div>
        
        <!-- Bootstrap JS and dependencies (optional) -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
        <script>
            window.onload = function () {
                const form = document.getElementById("login-form");
                const error = document.getElementById("errorCapcha");

                form.addEventListener("submit", function (event) {
                    const response = grecaptcha.getResponse();
                    if (!response) {
                        event.preventDefault();
                        error.innerHTML = "Please complete the reCAPTCHA.";
                    }
                });
            }
        </script>
    </body>
</html>
