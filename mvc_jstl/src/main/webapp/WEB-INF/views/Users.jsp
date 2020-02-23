<jsp:useBean id="users" scope="request" type="java.util.List"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: STAS KOROBEYNIKOV
  Date: 23.02.2020
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<style type="text/css">
    table {
        border-collapse: collapse;
        border: 2px solid black;
    }
    td, th {
        padding: 5px;
        border: 1px solid black;
    }
</style>
<body>
<table>
    <tr>
        <th>ID</th><th>Name</th><th>Login</th><th>Email</th><th>CreateDate</th><th>Action</th>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.login}</td>
            <td>${user.email}</td>
            <td>${user.createDate}</td>
            <td>
                <form action="${pageContext.servletContext.contextPath}/users" method="post">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="id" value="${user.id}">
                    <input type="submit" value="DELETE">
                </form>
                <form action="${pageContext.servletContext.contextPath}/edit" method="get">
                    <input type="hidden" name="id" value="${user.id}">
                    <input type="submit" value="UPDATE">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
