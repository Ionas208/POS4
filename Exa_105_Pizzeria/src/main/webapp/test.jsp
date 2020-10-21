<%-- 
    Document   : test
    Created on : 21.10.2020, 10:56:59
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
        <h1>Hello World!</h1>
        <select>
            <c:forEach begin="1" end="10" var="i">
                <option>${i}</option>
            </c:forEach>
        </select>
        
    </body>
</html>
