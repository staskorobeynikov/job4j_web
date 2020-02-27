<%--
  Created by IntelliJ IDEA.
  User: STAS KOROBEYNIKOV
  Date: 27.02.2020
  Time: 11:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
</head>
<c:if test="${error != ''}">
    <div style="background-color:red">
        <c:out value="${error}"/>
    </div>
</c:if>
<body>
    <form action="${pageContext.servletContext.contextPath}/signin" method="post">
        <table>
            <tr><td>Login :</td><td><input type="text" name="login"></td></tr>
            <tr><td>Password</td><td><input type="password" name="password"></td></tr>
        </table>
        <input type="submit" value="Войти">
    </form>
</body>
</html>
