<jsp:useBean id="oneUser" scope="request" type="ru.job4j.model.User"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>One User</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<style type="text/css">
    table.table-bordered {
        border-collapse: collapse;
        border: 2px solid black;
        margin-top: 12px;
        margin-left: 12px;
    }
    th {
        text-align: center;
    }
    td, th {
        padding: 5px;
        border: 1px solid black;
    }
    div {
        padding: 5px;
    }
    .exit {
        position: fixed;
        right: 5px;
        top: 5px;
    }
</style>
<body>
<div>
    <table class="table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Login</th>
            <th>Password</th>
            <th>Role</th>
            <th>Email</th>
            <th>Country</th>
            <th>City</th>
            <th>CreateDate</th>
            <th>PhotoID</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${oneUser.id}</td>
            <td>${oneUser.name}</td>
            <td>${oneUser.login}</td>
            <td>${oneUser.password}</td>
            <td>${oneUser.roleName}</td>
            <td>${oneUser.email}</td>
            <td>${oneUser.country}</td>
            <td>${oneUser.city}</td>
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
        </tbody>
    </table>
</div>
<form class="exit" action="${pageContext.servletContext.contextPath}/exit" method="post">
    <button type="submit" class="btn btn-default" style="background-color: lightgray">Exit</button>
</form>
</body>
</html>
