<jsp:useBean id="find" scope="request" type="ru.job4j.model.User"/>
<jsp:useBean id="id" scope="request" type="java.lang.String"/>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserUpdate</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:useBean id="roleUser" scope="session" type="java.lang.String"/>
<c:choose>
    <c:when test="${roleUser == 'user'}">
        <form class="form-horizontal" action="${pageContext.servletContext.contextPath}/edit?id=${id}" method="post">
            <div class="form-group">
                <label class="control-label col-sm-2" for="ID">ID user:</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" id="ID" name="id" value="${find.id}" required>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="name">Name user:</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" id="name" name="name" value="${find.name}" required>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="password">Password user:</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" id="password" name="password" value="${find.password}" required>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="email">Email user:</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" id="email" name="email" value="${find.email}" required>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="createDate">Create date user:</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" id="createDate" name="createDate" value="${find.createDate}" required>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="country">Country user:</label>
                <div class="col-sm-5">
                    <select multiple-class="form-control" id="country" name="country">
                        <option value="${param["country"]}" selected>${find.country}</option>
                        <c:forEach items="${listCountries}" var="land">
                            <option value="${land}">${land}</option>
                        </c:forEach>
                    </select>
                </div>
                <script>
                    $("#country").change(function () {
                        let selectCountry = $(this).val();
                        $.ajax({
                            type: 'POST',
                            url: '${pageContext.servletContext.contextPath}/cities',
                            data: 'land=' + selectCountry,
                            dataType: 'json',
                            success: function(data) {
                                let cities = "";
                                for (let i = 0; i <data.length; i++) {
                                    cities += "<option value=" + data[i] + ">" + data[i] + "</option>";
                                }
                                $('#city').html(cities);
                            }
                        });
                    });
                </script>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="city">City user:</label>
                <div class="col-sm-5">
                    <select multiple-class="form-control" id="city" name="city">
                        <option value="${param["city"]}" selected>${find.city}</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-5">
                    <input type="hidden" id="login" name="login" value="${find.login}">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-5">
                    <input type="hidden" name="rolename" id="role" value="${find.roleName}">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-5">
                    <input type="hidden" name="file" id="file" value="${find.image}">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2"></label>
                <div class="col-sm-5">
                    <input type="submit">
                </div>
            </div>
        </form>
    </c:when>
    <c:otherwise>
        <form class="form-horizontal" action="${pageContext.servletContext.contextPath}/edit?id=${id}" method="post">
            <div class="form-group">
                <label class="control-label col-sm-2" for="ID1">ID user:</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" id="ID1" name="id" value="${find.id}" required>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="name1">Name user:</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" id="name1" name="name" value="${find.name}" required>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="login1">Login user:</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" id="login1" name="login" value="${find.login}">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="password1">Password user:</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" id="password1" name="password" value="${find.password}" required>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="email1">Email user:</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" id="email1" name="email" value="${find.email}" required>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="createDate1">Create date user:</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" id="createDate1" name="createDate" value="${find.createDate}" required>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="country1">Country user:</label>
                <div class="col-sm-5">
                    <select class="form-control" id="country1" name="country">
                        <option value="${param["country"]}" selected>${find.country}</option>
                        <c:forEach items="${listCountries}" var="land">
                            <option value="${land}">${land}</option>
                        </c:forEach>
                    </select>
                </div>
                <script>
                    $("#country1").change(function () {
                        let selectCountry = $(this).val();
                        $.ajax({
                            type: 'POST',
                            url: '${pageContext.servletContext.contextPath}/cities',
                            data: 'land=' + selectCountry,
                            dataType: 'json',
                            success: function(data) {
                                let cities = "";
                                for (let i = 0; i <data.length; i++) {
                                    cities += "<option value=" + data[i] + ">" + data[i] + "</option>";
                                }
                                $('#city1').html(cities);
                            }
                        });
                    });
                </script>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="city1">City user:</label>
                <div class="col-sm-5">
                    <select class="form-control" id="city1" name="city">
                        <option value="${param["city"]}" selected>${find.city}</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="role1">Role user:</label>
                <div class="col-sm-5">
                    <select class="form-control" id="role1" name="rolename">
                        <option value="${find.roleName}" selected>${find.roleName}</option>
                        <option value="admin">Admin</option>
                        <option value="user">User</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-5">
                    <input type="hidden" name="file" id="file1" value="${find.image}">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2"></label>
                <div class="col-sm-5">
                    <input type="submit">
                </div>
            </div>
        </form>
    </c:otherwise>
</c:choose>
</body>
</html>
