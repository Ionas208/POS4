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
        ${current_weather}
        <img src="http://openweathermap.org/img/wn/${current_weather.weather.icon}@2x.png">
       
        
    </body>
    <script>
        function changeLanguage(){
            let lang = document.getElementById('languageSelector').value;
            document.cookie = "lang="+lang+";"
            location.reload();
        }
    </script>
</html>
