<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
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
        <link rel="stylesheet" href="style.css">
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
            <button type="button" class="collapsible"><a href="${item.link}">${item.title}</a></button>
            <div class="content">
                ${item.description}
            </div>
        </c:forEach>
    </c:forEach>
    </body>
</html>

<script>
    var coll = document.getElementsByClassName("collapsible");
    var i;

    for (i = 0; i < coll.length; i++) {
      coll[i].addEventListener("click", function() {
        this.classList.toggle("active");
        var content = this.nextElementSibling;
        if (content.style.maxHeight){
          content.style.maxHeight = null;
        } else {
          content.style.maxHeight = content.scrollHeight + "px";
        }
      });
    }
</script>
