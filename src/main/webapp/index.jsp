<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 21/07/2023
  Time: 10:37 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome</title>
    <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Poppins" />
    <link rel="stylesheet" href="util/css/index.css">
</head>
<body>
<div class="bg-form">
    <div class="bg_imgform">
        <img id="bgpic1" src="util/images/bg.jpg" alt="BG" width="100%">
        <div class="bgTextcenter">
            <h1>Store Management System</h1>
        </div>
        <div id="text-block">
            <!-- This button will trigger the Basic Authentication pop-up -->
            <button id="loginForm" style="font-size: 15pt; text-align:center; color: white;" onclick="redirectToCustomerPage()">ENTER!</button>
        </div>

    </div>


</div>

<script>
    function redirectToCustomerPage() {
        const apiUrl = "api/customers"; // Replace with the actual API endpoint URL

        // Use fetch API to make a GET request to the API
        fetch(apiUrl, {
            method: "GET"
        })
            .then((response) => {
                if (response.ok) {
                    // Successful login
                    window.location.href = "CustomerPage.jsp"; // Redirect to CustomerPage.jsp
                } else if (response.status === 401) {
                    // Failed login - Unauthorized
                    alert("Invalid credentials. Please try again.");
                } else {
                    // Other errors
                    alert("An error occurred. Please try again later.");
                }
            })
            .catch((error) => {
                // Handle errors, if any
                console.error("Error:", error);
                alert("An error occurred. Please try again later.");
            });
    }
</script>

</body>
</html>
