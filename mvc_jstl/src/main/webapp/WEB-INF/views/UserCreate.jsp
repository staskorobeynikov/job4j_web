<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserCreate</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<style type="text/css">
    div.error {
        text-align: center;
    }
</style>
<body>
<div class="error">
    <div style="background-color:red" id="invalid">
    </div>
    <div style="background-color:lightgreen" id="valid">
    </div>
</div>
<form class="form-horizontal" action="${pageContext.servletContext.contextPath}/create" method="post" enctype="multipart/form-data">
    <div class="form-group">
        <label class="control-label col-sm-2" for="ID">ID user:</label>
        <div class="col-sm-5">
            <input type="text" class="form-control" id="ID" name="id" placeholder="Enter ID" required>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="name">Name user:</label>
        <div class="col-sm-5">
            <input type="text" class="form-control" id="name" name="name" placeholder="Enter name" required>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="login">Login user:</label>
        <div class="col-sm-5">
            <input type="text" class="form-control" id="login" name="login" placeholder="Enter login" required>
        </div>
        <script>
            $("#login").focusout(function () {
                let selectLogin = $(this).val();
                $.ajax({
                    type: 'POST',
                    url: '${pageContext.servletContext.contextPath}/logins',
                    data: 'login=' + selectLogin,
                    dataType: 'json',
                    success: function (data) {
                        let valid = "";
                        if (data !== '') {
                            $('#valid').empty();
                            valid = "<c:out value="Invalid user login"/>";
                            $("#login").val('');
                            $('#invalid').html(valid);
                        } else {
                            $('#invalid').empty();
                            valid = "<c:out value="Correctly user login"/>";
                            $('#valid').html(valid);
                        }
                    }
                })
            })
        </script>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="password">Password user:</label>
        <div class="col-sm-5">
            <input type="text" class="form-control" id="password" name="password" placeholder="Enter password" required>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="email">Email user:</label>
        <div class="col-sm-5">
            <input type="text" class="form-control" id="email" name="email" placeholder="Enter email" required>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="country">Country user:</label>
        <div class="col-sm-5">
            <select multiple-class="form-control" id="country" name="country">
                <c:forEach items="${countries}" var="country">
                    <option value="${country}">${country}</option>
                </c:forEach>
            </select>
            <script>
                $("#country").change(function () {
                    let selectCountry = $(this).val();
                    $.ajax({
                        type: 'POST',
                        url: '${pageContext.servletContext.contextPath}/cities',
                        data: 'town=' + selectCountry,
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
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="city">City user:</label>
        <div class="col-sm-5">
            <select multiple-class="form-control" id="city" name="city">
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="createDate">Create date user:</label>
        <div class="col-sm-5">
            <input type="text" class="form-control" id="createDate" name="createDate" placeholder="Enter create date" required>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="role">Role user:</label>
        <div class="col-sm-5">
            <select multiple-class="form-control" id="role" name="rolename">
                <option value="admin">Admin</option>
                <option value="user">User</option>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="file">Photo user:</label>
        <div class="col-sm-5">
            <input type="file" class="form-control-file" id="file" name="file" required>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2"></label>
        <div class="col-sm-5">
            <input type="submit">
        </div>
    </div>
</form>
</body>
</html>
