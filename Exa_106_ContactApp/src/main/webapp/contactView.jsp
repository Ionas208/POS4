<%-- 
    Document   : contactView
    Created on : 18.11.2020, 17:33:15
    Author     : 10jon
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style_1.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://kit.fontawesome.com/dd041cd8d6.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Open+Sans" />
        <title>Contact List</title>
    </head>
    <body>
        <form method="POST" action="./ContactController">
            <h1>Contact List</h1>
            <div class="control_container">
                <div class="filter_container">
                    <table class="filter_table">
                    <tr>
                        <td>Filter by</td>
                        <td>
                            <select name="filterby">
                                <option value="NONE"></option>
                                <option value="NAME">Name</option>
                                <option value="REGEX">Name Pattern</option>
                                <option value="COMPANY">Company</option>
                                <option value="GENDER">Gender</option>
                            </select>
                            :
                        </td>
                        <td>
                            <input type="text" name="filter" placeholder="Enter filter value" autocomplete="off"/>
                        </td>
                    </tr>
                </table>
                </div>
                
                <div class="sort_container">
                    <table class="sort_table">
                        <thead>
                            <tr>
                                <th><b>Sorting Order</b></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach begin="1" end="3" var="i">
                                <tr>
                                    <td>Option ${i}</td>
                                    <td>
                                        <select name="option${i}">
                                            <option value="NONE"></option>
                                            <option value="FIRSTNAME">Firstname</option>
                                            <option value="LASTNAME">Lastname</option>
                                            <option value="COMPANY">Company</option>
                                            <option value="AGE">Age</option>
                                    </select>
                                    </td>
                                    <td>
                                        <select name="option${i}_order">
                                            <option value="ASC">ASC</i></option>
                                            <option value="DESC">DESC</i></option>
                                        </select>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                
                <div class="submit_container"><input type="submit" value="Submit"/></div>
                
                <c:forEach items="${errors}" var="error">
                    <div class="error">
                        ${error}
                    </div>
                </c:forEach>
            </div>
            <div class="all_contacts">
            <c:forEach items="${clm.getContacts()}" var="contact">
                <div class="contact_container">
                    <div class="left">
                        <input type="checkbox" name="${contact.getId()}" title="Check to delete with submit"/>
                        <img src='https://upload.wikimedia.org/wikipedia/commons/thumb/9/98/OOjs_UI_icon_userAvatar.svg/1024px-OOjs_UI_icon_userAvatar.svg.png'
                             width='100px' height='100px'>
                    </div>
                    <div class="right">
                        <table>
                            <tr>
                                <td class="description_row">Name:</td>
                                <td>${contact.getFirstname()} ${contact.getLastname()}</td>
                            </tr>
                            <tr>
                                <td class="description_row">E-Mail:</td>
                                <td>
                                    <c:forEach var="email" items="${contact.getEmail()}">
                                        <div>${email} </div>
                                    </c:forEach>
                                </td>
                            </tr>
                            <tr>
                                <td class="description_row">Gender:</td>
                                <td>${contact.getGender().getGender()}</td>
                            </tr>
                            <tr>
                                <td class="description_row">Date of Birth:</td>
                                <td>${contact.getDateOfBirth()}</td>
                            </tr>
                            <tr>
                                <td class="description_row">Company:</td>
                                <td>${contact.getCompany().getName()}/${contact.getCompany().getStockmarket()}</td>
                            </tr>
                        </table>
                    </div>
                    
                    
                </div>
            </c:forEach>
            </div>
        </form>
    
    </body>
</html>
