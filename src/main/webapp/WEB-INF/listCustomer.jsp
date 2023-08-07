<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 8/3/2023
  Time: 2:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        h1,table{
            width: 60%;
            text-align: center;
            margin: 0 auto 50px;
        }
    </style>
</head>
<body>
<h1>List Customer</h1>
<form action="<%=request.getContextPath()%>/CustomerServlet">
    <input type="text" id="search" name="search" width="200px" on/>
    <input type="submit" value="SEARCH" name="action" />
</form>
<h2><a href="<%=request.getContextPath()%>/CustomerServlet?action=ADD">ADD</a></h2>

    <thead>
    <tr>
        <th>STT</th>
        <th>Name</th>
        <th>Email</th>
        <th>Address</th>
        <th colspan="2">Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${customers}" var="c" varStatus="item">
        <tr>
            <td>${item.count}</td>
            <td>${c.name}</td>
            <td>${c.email}</td>
            <td>${c.address}</td>
            <td><a href="<%=request.getContextPath()%>/CustomerServlet?action=EDIT&id=${c.id}">Edit</a></td>
            <td><a onclick="return confirm('Do you want to delete Customer')" href="<%=request.getContextPath()%>/CustomerServlet?action=DELETE&id=${c.id}">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
