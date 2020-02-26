<%--
  Created by IntelliJ IDEA.
  User: Augustin
  Date: 25.02.2020
  Time: 21:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update User</title>
    <link rel="stylesheet" href="/my_crud_app_war_exploded/views/style.css">
</head>
<body>
    <!-- Почему то ломается верстка, временно поставил звездочки -->
    <div id="**grid" class="**user">
        <form action="/my_crud_app_war_exploded/update" method="post">
            <input type="hidden" name = "id" value="${param.id}">
            <input type="text" name="name" value="${param.name}" placeholder=${param.name}>
            <input type="text" name="password" value="${param.password}" placeholder=${param.password}>
            <input type="hidden" name="_method" value="put">
            <input type="submit" value="Обновить">
        </form>
    </div>
</body>
</html>
