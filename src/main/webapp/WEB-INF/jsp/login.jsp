<!-- 
- Author(s): Pragnya Kondrakunta 
- Date: Dec, 2023
- Description: Authentication Page View to login, signup and reset password -->

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype HTML>
<html>

<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>

    <style>
        .btn-theme {
            background-color: #fff;
            border-color: #4776E6;
            color: #4776E6;

        }

        .btn-theme:hover {
            transition-duration: 0.1s;
            border-color: #fff;
            color: #fff;
            background-image: linear-gradient(to right, #4776E6, #8E54E9);

        }

        .authbox {
            padding: 16px;
            width: 500px;
            text-align: center;
            border: 1px solid #9d9d9d;
            border-radius: 10px;
            margin-left: auto;
            margin-right: auto;
            box-shadow: 2px 2px 2px #888888;
        }

        /* Full-width input fields */
        input[type="text"],
        input[type="password"] {
        width: 80%;
        padding: 12px 20px;
        margin: 8px 0;
        border: 1px solid #ccc;
        box-sizing: border-box;
        }
    </style>

</head>

<body class="container  text-center">
    <br /><br /><br /><br />
    <h3>Community HOA</h3>
    <br /><br />
    <div class="authbox">
        <h2>Login</h2>
        <form method="POST">
            <input type="text" placeholder="Enter Username" name="uname" id="user" required /><br />
            <input type="password" placeholder="Enter Password" name="psw" id="psw" required /><br />
            <a href="#">Forgot password?</a>
            <br /><br/>
            <button type="submit" class="btn btn-lg btn-theme">Login</button>
        </form>
        <br/>
        <a href="/auth/signup">Not a user? Signup here.</a>
    </div>
</body>

</html>