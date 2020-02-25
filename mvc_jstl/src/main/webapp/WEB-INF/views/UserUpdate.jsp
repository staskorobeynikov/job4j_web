<jsp:useBean id="find" scope="request" type="ru.job4j.model.User"/>
<jsp:useBean id="id" scope="request" type="java.lang.String"/>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: STAS KOROBEYNIKOV
  Date: 23.02.2020
  Time: 18:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserUpdate</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/edit?id=${id}" method="post">
    ID: <label><input type="text" name="id" value="${find.id}"></label><br/><br/>
    Name: <label><input type="text" name="name" value="${find.name}"></label><br/><br/>
    Login: <label><input type="text" name="login" value="${find.login}"></label><br/><br/>
    Email: <label><input type="text" name="email" value="${find.email}"></label><br/><br/>
    CreateDate: <label><input type="text" name="createDate" value="${find.createDate}"></label><br/><br/>
    Photo: <input type="file" name="file"><br/><br/>
    <table>
        <tr><th>Current photo</th></tr>
        <tr><td><img src="${pageContext.servletContext.contextPath}/download?name=${find.image}" width="100px" height="100px"/></td></tr>
    </table>
    <input type="submit">
</form>
</body>
</html>
