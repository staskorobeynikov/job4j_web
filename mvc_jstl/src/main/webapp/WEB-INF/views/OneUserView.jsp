<jsp:useBean id="oneUser" scope="request" type="ru.job4j.model.User"/>
<%--
  Created by IntelliJ IDEA.
  User: STAS KOROBEYNIKOV
  Date: 27.02.2020
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>One User</title>
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
        <th>ID</th>
        <th>Name</th>
        <th>Login</th>
        <th>Password</th>
        <th>Role</th>
        <th>Email</th>
        <th>CreateDate</th>
        <th>PhotoID</th>
        <th>Action</th>
    </tr>
    <tr>
        <td>${oneUser.id}</td>
        <td>${oneUser.name}</td>
        <td>${oneUser.login}</td>
        <td>${oneUser.password}</td>
        <td>${oneUser.roleName}</td>
        <td>${oneUser.email}</td>
        <td>${oneUser.createDate}</td>
        <td>
            <table>
                <tr>
                    <td>
                        <img src="${pageContext.servletContext.contextPath}/download?name=${oneUser.image}" width="100px" height="100px"/>
                    </td>
                </tr>
                <tr>
                    <td align="middle">
                        ${oneUser.image}
                    </td>
                </tr>
                <tr>
                    <td align="middle">
                        <a href="${pageContext.servletContext.contextPath}/download?name=${oneUser.image}">Download</a>
                    </td>
                </tr>
            </table>
        </td>
        <td>
            <form action="${pageContext.servletContext.contextPath}/users" method="post">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="id" value="${oneUser.id}">
                <input type="submit" value="DELETE">
            </form>
            <form action="${pageContext.servletContext.contextPath}/edit" method="get">
                <input type="hidden" name="id" value="${oneUser.id}">
                <input type="submit" value="UPDATE">
            </form>
        </td>
    </tr>
</table>
<form action="${pageContext.servletContext.contextPath}/exit" method="post">
    <button type="submit" class="btn btn-default" style="background-color: lightgray">Exit</button>
</form>
</body>
</html>
