<%-- 
    Document   : substitudePlanView
    Created on : 04.11.2020, 17:47:36
    Author     : 10jon
--%>

<%@page import="beans.Weekday"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel = "stylesheet" type = "text/css" href = "style.css" />
        <title>Supplierplan</title>
    </head>
    <body>
        <h1>Supplierplan</h1>
        <form>
            <table>
                <tbody>
                    <tr>
                        <td>
                            <label>Tag:</label>
                        </td>
                        <td>
                            <select name="weekday">
                                <c:set var="weekdays" value="<%=Weekday.values()%>"/>
                                <c:forEach var="weekday" items="${weekdays}">
                                    <option>${weekday.getDay_full()}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Stunde:</label>
                        </td>
                        <td>
                            
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Fach:</label>
                        </td>
                        <td>
                            
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Lehrer:</label>
                        </td>
                        <td>
                            
                        </td>
                    </tr>
                </tbody>
            </table>
        </form>
                                
        <table class="timetable">
            <thead>
                <c:forEach var="weekday" items="${weekdays}">
                        <th>${weekday.getDay_short()}</th>
                </c:forEach>
            </thead>
            <tbody>
                <c:forEach var="hour" begin="1" end="10">
                    <tr>
                        <c:forEach var="lesson" items="${timetable.getLessonsForHour(hour)}">
                            <td>
                                <div><b>${lesson.getSubject()}</b></div>
                                <div>
                                    <c:forEach var="teacher" items="${lesson.getTeachers()}">
                                        <span>${teacher} </span>
                                    </c:forEach>
                                </div>
                            </td>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
