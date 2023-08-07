<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 8/3/2023
  Time: 2:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Add new Student</h1>
<form action="/CustomerServlet" method="post">
    <label for="name">Name</label>
    <input type="text" id="name" name="name">
    <label for="email">Email</label>
    <input type="text" id="email" name="email">
    <label for="address">Address</label>
    <input type="text" id="address" name="address">
    <input type="submit" value="ADD" name="action"/>
</form>
</body>
</html>
