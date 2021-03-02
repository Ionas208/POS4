<%-- 
    Document   : RSSView
    Created on : 02.03.2021, 19:58:23
    Author     : 10jon
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RSS</title>
    </head>
    <body>
        <h1>RSS</h1>
        <form action="./RSSServlet" method="POST">
            <input type="text" name="rss">
            <input type="submit" value="add feed">
        </form>
    <c:forEach items="${channels}" var="channel">
        <h2>${channel.title}</h2>
        <c:forEach items="${channel.items}" var="item">
            <a href="${item.link}"><h3>${item.title}</h3></a>
            <hr>
        </c:forEach>
    </c:forEach>
    </body>
</html>
