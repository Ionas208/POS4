<%-- 
    Document   : substitudePlanView
    Created on : 04.11.2020, 17:47:36
    Author     : 10jon
--%>

<%@page import="beans.Lesson"%>
<%@page import="bl.SubstitudePlan"%>
<%@page import="beans.Weekday"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" type = "text/css" href = "style.css" />
        <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Open+Sans" />
        <title>${timetable.getClassname()}</title>
    </head>
    <body>
        <div>
            <div class="upper">
                <h1>Supplierplan ${timetable.getClassname()}</h1>
                <form action="./SubstitudeController" method="POST" onsubmit="return validate()">
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
                                            <option value="${weekday}">${weekday.getDay_full()}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label>Stunde:</label>
                                </td>
                                <td>
                                    <select name="hour">
                                        <c:forEach var="hour" begin="1" end="10">
                                            <option>${hour}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label>Fach:</label>
                                </td>
                                <td>
                                    <input id="subject" type="text" name="subject" autocomplete="off">
                                    <span id="subject_error" style="display: ${subject_error}">Bitte Fach eingeben!</span>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label>Lehrer:</label>
                                </td>
                                <td>
                                    <input id="teachers" type="text" name="teachers" placeholder="T1, T2, ..." autocomplete="off">
                                    <span id="teachers_error" style="display: ${teachers_error}">Bitte Lehrer eingeben!</span>
                                </td>
                            </tr>
                            <tr>
                                <td><input type="submit" value="Übernehmen"/></td>
                            </tr>
                        </tbody>
                    </table>
                    <span id="other_error" style="display: ${other_error}">Ein Fehler ist aufgetreten!</span>
                </form>
            </div>
            

            <table class="timetable">
                <thead>
                    <th class="timetable_headline"/>
                    <c:forEach var="weekday" items="${weekdays}">
                            <th class="timetable_headline">${weekday.getDay_short()}</th>
                    </c:forEach>
                </thead>
                <tbody>
                    <c:forEach var="hour" begin="1" end="10">
                        <tr>
                            <td class="timetable_hour"><b>${hour}</b></td>
                            <c:forEach var="lesson" items="${timetable.getLessonsForHour(hour)}">
                                <td class="${lesson.getStyleClasses()}">                               
                                    <div><b>${lesson.getSubject()}</b></div>
                                    <div>
                                        <c:forEach var="teacher" items="${lesson.getTeachers()}">
                                            <span><small>${teacher}</small></span>
                                        </c:forEach>
                                    </div>
                                </td>
                            </c:forEach>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
<script>
    function validate(){
        let subject = document.getElementById("subject").value;
        let teachers = document.getElementById("teachers").value;
        let return_val = true;

        if(subject == ""){
            document.getElementById("subject_error").style.display = "inline";
            return_val = false;
        }
        else{
            document.getElementById("subject_error").style.display = "none";
        }

        if(teachers == ""){
            document.getElementById("teachers_error").style.display = "inline";
            return_val = false;
        }
        else{
            document.getElementById("teachers_error").style.display = "none"
        }
        return return_val;
    }
</script>