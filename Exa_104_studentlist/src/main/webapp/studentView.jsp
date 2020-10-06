<%-- 
    Document   : studentView
    Created on : 28.09.2020, 08:25:24
    Author     : 10jon
--%>

<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="bl.Student"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="student_style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://kit.fontawesome.com/dd041cd8d6.js" crossorigin="anonymous"></script>
        <title>Student List</title>
    </head>
    <body>
        <h1>Student List</h1>
        <div class="filter">
            <form method="post" action="/Exa_104_studentlist/StudentController">
                <label>Filter: </label> 
                <%
                    String filter = (String)request.getAttribute("filter");
                    if(filter == null){
                        filter = "";
                    }
                    out.println("<input id='filterLabel' type='text' name='filter' value='"+filter+"'>");
                %>
                
                <button class="formButton">Filter setzten</button> 
                <button class="formButton" onclick="resetFilter()">Filter entfernen</button>
                <br>
                <label>Schüler auswählen: </label>
                <select onchange="submit();" name="catNr;class">
                    <%
                        List<Student> students = (List<Student>) request.getAttribute("students");
                        Student selectedStudent = (Student) request.getAttribute("selectedStudent");

                        if (selectedStudent == null && students.size() > 0) {
                            selectedStudent = students.get(0);
                            students.remove(selectedStudent);
                        } else if (selectedStudent != null) {
                            students.remove(selectedStudent);
                        }
                        if (selectedStudent != null) {
                            out.println("<option value=" + selectedStudent.getCatNr() + ">");
                            out.println(selectedStudent.getLastname().toUpperCase() + " " + selectedStudent.getFirstname());
                            out.println("</option>");
                        }
                        if (students != null && students.size() > 0) {
                            for (Student s : students) {
                                out.println("<option value=" + s.getCatNr() +";"+s.getClassname()+ ">");
                                out.println(s.getLastname().toUpperCase() + " " + s.getFirstname());
                                out.println("</option>");
                            }
                        }
                    %>
                </select>
            </form>
        </div>

        <div class="student">
            <div id="imageContainer">
                <i class="fas fa-user"></i>
            </div>
            <div id="dataContainer">
                <table>
                    <tbody>
                        <tr>
                            <td class="dataHeadline">Name: </td>
                            <%
                                if (selectedStudent != null) {
                                    out.println("<td>");
                                    out.println(selectedStudent.getLastname() + " "
                                            + selectedStudent.getFirstname());
                                    out.println("</td>");
                                }

                            %>
                        </tr>
                        <tr>
                            <td class="dataHeadline">Katalognummer: </td>
                            <%                                
                                if (selectedStudent != null) {
                                    out.println("<td>");
                                    out.println(selectedStudent.getCatNr());
                                    out.println("</td>");
                                }

                            %>
                        </tr>
                        <tr>
                            <td class="dataHeadline">Klasse: </td>
                            <%  
                                if (selectedStudent != null) {
                                    out.println("<td>");
                                    out.println(selectedStudent.getClassname());
                                    out.println("</td>");
                                }

                            %>
                        </tr>
                        <tr>
                            <td class="dataHeadline">Geschlecht: </td>
                            <%                                
                                if (selectedStudent != null) {
                                    out.println("<td>");
                                    out.println(selectedStudent.getGender().toUpperCase());
                                    out.println("</td>");
                                }

                            %>
                        </tr>
                        <tr>
                            <td class="dataHeadline">Geburtsdatum: </td>
                            <%          
                                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                                if (selectedStudent != null) {
                                    out.println("<td>");
                                    out.println(dtf.format(selectedStudent.getDateOfBirth()));
                                    out.println("</td>");
                                }
                            %>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
<script>
    function resetFilter() {
        document.getElementById("filterLabel").value = "";
        submit();
    }
</script>
