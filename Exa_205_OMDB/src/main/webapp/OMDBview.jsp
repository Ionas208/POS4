<%-- 
    Document   : OMDBview
    Created on : 22.12.2020, 09:40:53
    Author     : 10jon
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>OMDB</title>
        <link rel="stylesheet" type="text/css" href="style.css" />
    </head>
    <body>
        <div class="header">
            <h1>OMDB</h1>
        </div>
        
        <form method="POST" action="./OMDBController">
            <div class="container">
                <div class="controls">
                    
                    <div class="sort">
                        <label>Sort by</label>
                        <select name="sort">
                            <option value="${sort}">${sort}</option>
                            <c:if test="${sort != ''}">
                                <option value=""></option>
                            </c:if>
                            <c:if test="${sort != 'title'}">
                                <option value="title">title</option>
                            </c:if>
                            <c:if test="${sort != 'date'}">
                                <option value="date">release date</option>
                            </c:if>
                        </select>
                        <select name="sort_order">
                            <option value="${sort_order}">${sort_order}</option>
                            <c:if test="${sort_order != 'ASC'}">
                                <option value="ASC">ASC</option>
                            </c:if>
                            <c:if test="${sort_order != 'DESC'}">
                                <option value="DESC">DESC</option>
                            </c:if>
                        </select>
                    </div>
                    
                    <div class="genre">
                        <label>Genre</label>
                        <select name="genre">
                            <option value="${genre}">${genre}</option>
                            <c:if test="${genre != ''}">
                                <option value=""></option>
                            </c:if>
                            <c:forEach items="${genres}" var="g">
                                <c:if test="${genre != g}">
                                    <option value="${g}">${g}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                    
                    <div class="search">
                        <label>Search</label>
                        <input type="text" name="search" value="${search}">
                    </div> 
                    
                    <div class="submit">
                        <input type="submit" value="Suchen">
                    </div>
                </div>
                
                <div class="movies">
                    <table>
                        <thead>
                            <th></th>
                            <th>Title</th>
                            <th>Year</th>
                            <th>Genres</th>
                            <th>Type</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${movies}" var="movie">
                                <tr>
                                    <td><img src="${movie.getPoster()}" width="75px" height="150px"></td>
                                    <td class="titles"><b>${movie.getTitle()}</b></td>
                                    <td>${movie.getYear()}</td>
                                    <td>${movie.getGenre()}</td>
                                    <td>${movie.getType()}</td>
                                    <td><a href="./OMDBController?id=${movie.getImdbID()}">Details</a></td>
                                </tr>   
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                
            </div>
        
        <select name="p" onchange="submit()">
            <option value="${page}">${page}</option>
            <c:forEach begin="1" end="${pages}" var="p">
                <c:choose>
                    <c:when test="${!(page == p)}">
                        <option value="${p}">${p}</option>
                    </c:when>
                </c:choose>            
            </c:forEach>
        </select>
        </form>
    </body>
</html>
