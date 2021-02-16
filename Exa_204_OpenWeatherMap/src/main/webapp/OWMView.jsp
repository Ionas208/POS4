<%-- 
    Document   : OWMView
    Created on : 02.02.2021, 17:17:30
    Author     : 10jon
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>OWM</h1>
        <form action="./OWMController" method="POST">
            <input type="text" name="search">
            <input type="submit" value="${translation.get("search").getValue(language)}">
        </form>
        ${current_weather}
        <img src="http://openweathermap.org/img/wn/${current_weather.weather.icon}@2x.png">
        <form action="./OWMController" method="POST">
            <select name="language" onChange="changeLanguage()" id="languageSelector">
                <option value="${language.name()}">${language.langFull}</option>
                <c:out value="asd"></c:out>
                <c:forEach items="${languages}" var="l">
                    <c:if test="${!(l==language)}">
                        <option value="${l.name()}">${l.langFull}</option>
                    </c:if>
                </c:forEach>
            </select> 
        </form>
        
    </body>
    <script>
        function changeLanguage(){
            let lang = document.getElementById('languageSelector').value;
            document.cookie = "lang="+lang+";"
            location.reload();
        }
    </script>
</html>
