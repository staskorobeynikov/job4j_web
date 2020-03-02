<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<style type="text/css">
    div.error {
        text-align: center;
    }
</style>
<script>
    function validate() {
        let result = true;
        let answer = '';
        let elements = [$("#login"), $("#password")];
        for (let i = 0; i < elements.length; i++) {
            if (elements[i].val() === '') {
                answer += elements[i].attr("placeholder") + "\n";
                result = false;
            }
        }
        if (!result) {
            alert(answer);
        }
        return result;
    }
</script>
<body>
    <form class="form-horizontal" action="${pageContext.servletContext.contextPath}/signin" method="post">
        <div class="error">
            <c:if test="${error != ''}">
                <div style="background-color:red">
                    <c:out value="${error}"/>
                </div>
            </c:if>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="login">Login user:</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" id="login" name="login" placeholder="Enter login">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="password">Login user:</label>
            <div class="col-sm-5">
                <input type="password" class="form-control" id="password" name="password" placeholder="Enter password">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default" onclick="return validate()">Login account</button>
            </div>
        </div>
    </form>
</body>
</html>
