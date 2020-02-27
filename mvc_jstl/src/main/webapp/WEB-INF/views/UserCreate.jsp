<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: STAS KOROBEYNIKOV
  Date: 23.02.2020
  Time: 17:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserCreate</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/create" method="post" enctype="multipart/form-data">
    <table>
        <tr><td>ID :</td><td><input type="text" name="id"></td></tr>
        <tr><td>Name :</td><td><input type="text" name="name"></td></tr>
        <tr><td>Login :</td><td><input type="text" name="login"></td></tr>
        <tr><td>Password :</td><td><input type="text" name="password"></td></tr>
        <tr><td>Email :</td><td><input type="text" name="email"></td></tr>
        <tr><td>CreateDate :</td><td><input type='text' name="createDate"></td></tr>
        <tr><td>Role :</td>
            <td>
                <select name="rolename">
                    <option value="admin">Admin</option>
                    <option value="user">User</option>
                </select>
            </td>
        </tr>
        <tr><td>Photo :</td><td><input type="file" name="file"></td></tr>
    </table>
    <input type="submit">
</form>
</body>
</html>
