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
<form action="${pageContext.servletContext.contextPath}/" method="post">
    ID: <label><input type="text" name="id"></label><br/><br/>
    Name : <label><input type="text" name="name"></label><br/><br/>
    Login : <label><input type="text" name="login"></label><br/><br/>
    Email : <label><input type="text" name="email"></label><br/><br/>
    CreateDate : <label><input type='text' name="createDate"></label><br/><br/>
    <input type="submit">
</form>
</body>
</html>
