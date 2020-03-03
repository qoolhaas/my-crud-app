<%--
  Created by IntelliJ IDEA.
  User: Augustin
  Date: 25.02.2020
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>List all users</title>
    <link rel="stylesheet" href="/css/style.css">
</head>

<body>
    <span class="link">
        <a href="/add">I want to add NEW USERS!</a>
    </span>

    <div id="grid">
        <c:forEach var="user" items="${users}" >
            <span class="user">
                <p>Id: ${user.getId()}</p>
                <p>Name: ${user.getName()}</p>
                <p>Password: ${user.getPassword()}</p>

                <form action="views/updateUser.jsp" method="post">
                    <input type="hidden" name="id" value="${user.getId()}">
                    <input type="hidden" name="name" value="${user.getName()}">
                    <input type="hidden" name="password" value="${user.getPassword()}">
                    <input class="butt" type="submit" value="Change">
                </form>

                <form action="/delete" method="post">
                    <input type="hidden" name="name" value="${user.getName()}">
                    <input class="butt" type="submit" value="Delete">
                </form>
            </span>

        </c:forEach>
    </div>
</body>
</html>
