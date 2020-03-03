<%--
  Created by IntelliJ IDEA.
  User: Augustin
  Date: 26.02.2020
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Go back</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
    <c:out value="${message}"/>
    <br>
    <a href="/list" id="link">Go back to list</a>
</body>
</html>
