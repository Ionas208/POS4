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
        <title>${movie.getTitle()}</title>
        <link rel="stylesheet" type="text/css" href="style_detail.css" />
    </head>
    <body>
        <div class="header">
            <h1>${movie.getTitle()}</h1>
        </div>
        <div class="container">
            <div class="details">
                <img src="${movie.getPoster()}" width="200px" height="400px">

                <div class="text_details">
                    <div>
                        <div class="plot">
                            <h2>Plot</h2>
                            <p>
                                ${movie.getPlot()}
                            </p>
                        </div>

                        <div class="data">
                            <table>
                                <tr>
                                    <td><b>Released:</b></td>
                                    <td>${movie.getReleased()}</td>
                                </tr>
                                <tr>
                                    <td><b>Runtime:</b></td>
                                    <td>${movie.getRuntime()}</td>
                                </tr>
                                <tr>
                                    <td><b>Director:</b></td>
                                    <td>${movie.getDirector()}</td>
                                </tr>
                                <tr>
                                    <td><b>Awards:  </b></td>
                                    <td>${movie.getAwards()}</td>
                                </tr>
                            </table>
                        </div>



                        <div class="actors">
                            <h3>Actors</h3>
                            <p>
                                ${movie.getActors()}
                            </p>
                        </div>            
                    </div>
                </div>
                            
            </div>
        </div>
        <button onclick="back()">Back</button>
        <script>
            function back(){
                window.history.back()
            }
        </script>
    </body>
</html>
