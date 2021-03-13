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
        <title>RSS Feed Reader</title>
        <link rel="stylesheet" href="style.css">
        <script src="https://kit.fontawesome.com/dd041cd8d6.js" crossorigin="anonymous"></script>
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap" rel="stylesheet">
    </head>
    <body>
        <h1>RSS Feed Reader</h1>
        <div class="input">
            <form action="./RSSServlet" method="POST">
                <select name="rss">
                    <c:forEach items="${default_channels}" var="channel">
                        <option value="${channel.url}">${channel.title}</option>
                    </c:forEach>
                </select>
                <input type="submit" value="subscribe">
            </form>
        </div>
        <c:if test="${error != null}">
            <div class="error">
                ${error}
            </div>
        </c:if>
    <c:forEach items="${channels}" var="channel">
        <div type="button" class="collapsible channel-head">
            <div class="channel-headline">
                ${channel.title}
            </div>
            <div class="channel-unsubscribe">
                <form action="./RSSServlet" method="POST">
                    <input type="text" hidden name="unsubscribe" value="${channel.link}">
                    <input type="submit" value="unsubscribe">
                </form>
            </div>
            
            
        </div>
        <div class="content">
        <c:forEach items="${channel.items}" var="item">
            <a href="${item.link}">
            <div class="article">
                <div class="article-image">
                    <img src="${item.content.url}">
                </div>
                <div class="article-text">
                    <div class="article-headline">
                        <h3>${item.title}</h3>
                    </div> 
                    <div class="article-description">
                        ${item.getDescriptionWithoutImage()}
                    </div>
                </div>
                <div class="article-read">
                    <form action="./RSSServlet" method="POST">
                        <button onclick="submit();">
                            <input type="text" hidden name="read" value="${item.guid}">
                            <c:choose>
                                <c:when test="${item.read}">
                                    <i class="fas fa-bookmark"></i>
                                </c:when>
                                <c:otherwise>
                                    <i class="far fa-bookmark"></i>
                                </c:otherwise>
                            </c:choose>
                        </button>
                    </form>
                </div>
                <div class="article-hide">
                    <form action="./RSSServlet" method="POST">
                        <button onclick="submit();">
                            <input type="text" hidden name="hide" value="${item.guid}">
                            <i class="far fa-eye-slash"></i>
                        </button>
                    </form>
                </div>
            </div>
            </a>
        </c:forEach>
        </div>
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
