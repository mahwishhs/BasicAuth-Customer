<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 23/07/2023
  Time: 8:14 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Poppins" />
    <link rel="stylesheet" href="util/css/CustomerPage.css">
</head>
<body>
<div id="theme" >
    <div class="bg_container">
        <div class = "menu">

            <ul>
                <li><a href="logout.php">Logout</a></li>
            </ul>
        </div>
        <div id="text-block">
            <h3 style ="top: 100px;font-size: 20pt; text-align:center; color: white;"><br>Welcome Back, Customer<br>What would you like to do?</h3>
            <br><br>
            <div class="grid1">
                <div class="box-1">
                    <button><a href="ViewC.jsp" style = "text-decoration: none; color: black;">View All Customers</a></button>
                </div>
                <div class="box-1">
                    <button><a href="AddC.jsp" style = "text-decoration: none; color: black;">Add Customer</a></button>
                </div>
                <div class="box-1">
                    <button><a href="DeleteC.jsp" style = "text-decoration: none; color: black;">Delete Customer</a></button>
                </div>
                <div class="box-1">
                    <button><a href="Update.jsp" style = "text-decoration: none; color: black;">Update Customer</a></button>
                </div>
            </div>

            <br><br><br><br><br><br>
        </div>
    </div>
</div>

</body>
</html>
