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
    <div id="grid">
        <c:forEach var="user" items="${users}" >
            <span class="user">
                <p>Id: ${user.getId()}</p>
                <p>Name: ${user.getName()}</p>
                <p>Password: ${user.getPassword()}</p>

                <form action="/delete" method="post">
                    <input type="hidden" name="id" value="${user.getId()}">
                    <input class="butt" type="submit" value="Delete">
                </form>
            </span>
        </c:forEach>
        <span class="user">
            <form action="/update" method="post">
                <p>Change User</p>
                <p>Current ID</p><input type="text" name="id" value="${param.id}">
                <p>New Name</p><input type="text" name="name" value="${param.name}">
                <p>New Pass</p><input type="text" name="password" value="${param.password}">
                <input class="butt" type="submit" value="Change">
            </form>
        </span>
        <span class="user">
            <form action="/add" method="post">
                <span>
                    <p>New User</p>
                    <p>Name:<input type="text" name="name"></p>
                    <br>
                    <p>Password:<input type="text" name="password"></p>
                    <br>
                    <button type="submit">Add me</button>
                </span>
            </form>
        </span>
    </div>
</body>
</html>
