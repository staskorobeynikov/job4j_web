<%@ page import="ru.job4j.model.User" %>
<%@ page import="ru.job4j.logic.ValidateService" %><%--
  Created by IntelliJ IDEA.
  User: STAS KOROBEYNIKOV
  Date: 22.02.2020
  Time: 21:33
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
    <% for (User user : ValidateService.getINSTANCE().findAll()) {%>
    <tr>
        <td><%=user.getId()%></td>
        <td><%=user.getName()%></td>
        <td><%=user.getLogin()%></td>
        <td><%=user.getEmail()%></td>
        <td><%=user.getCreateDate()%></td>
        <td>
            <form action="<%=request.getContextPath()%>/users" method="post">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="id" value="<%=user.getId()%>">
                <input type="submit" value="DELETE">
            </form>
            <form action="<%=request.getContextPath()%>/edit" method="get">
                <input type="hidden" name="id" value="<%=user.getId()%>">
                <input type="submit" value="UPDATE">
            </form>
        </td>
    </tr>
    <% } %>
</table>
</body>
</html>
