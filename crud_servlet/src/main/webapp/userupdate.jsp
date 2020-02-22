<%@ page import="ru.job4j.model.User" %>
<%@ page import="ru.job4j.logic.ValidateService" %><%--
  Created by IntelliJ IDEA.
  User: STAS KOROBEYNIKOV
  Date: 22.02.2020
  Time: 21:30
  To change this template use File | Settings | File Templates.
--%>

<%!
    User findUser(String i) {
        User result = null;
        for (User user : ValidateService.getINSTANCE().findAll()) {
            if (user.getId().equals(i)) {
                result = user;
                break;
            }
        }
        return result;
    }
%>

<%
    User find;
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserUpdate</title>
</head>
<body>
<%
    find = findUser(request.getParameter("id"));
%>
<form action="<%=request.getContextPath()%>/edit?id=<%=request.getParameter("id")%>" method="post">
    ID: <label><input type="text" name="id" value="<%=find.getId()%>"></label><br/><br/>
    Name: <label><input type="text" name="name" value="<%=find.getName()%>"></label><br/><br/>
    Login: <label><input type="text" name="login" value="<%=find.getLogin()%>"></label><br/><br/>
    Email: <label><input type="text" name="email" value="<%=find.getEmail()%>"></label><br/><br/>
    CreateDate: <label><input type="text" name="createDate" value="<%=find.getCreateDate()%>"></label><br/><br/>
    <input type="submit">
</form>
</body>
</html>
