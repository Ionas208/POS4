<%-- 
    Document   : OWMView
    Created on : 16.02.2021, 09:46:09
    Author     : 10jon
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Open Weather Map</title>
        <link rel="stylesheet" href="style.css"/>
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap" rel="stylesheet">
        <script src="https://kit.fontawesome.com/dd041cd8d6.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="header">
            <h1 id="logo">Open Weather Map</h1>
            
            <div id="search">
                <form action="./OWMController" method="POST">
                    <input type="text" name="search" placeholder="${translation.get("city").getValue(language)}">
                    <button onclick="submit();"><i class="fas fa-search"></i></button>
                </form>
            </div>
            
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
        </div>
    
    <c:if test="${current_weather != null}">
        <div class="container">
            <div class="summary">
                <img src="http://openweathermap.org/img/wn/${current_weather.weather.icon}@2x.png" width="200px%" height="200px%"><br>
                
                <div>
                    <div class="degrees">
                        ${current_weather.temperature.getRoundedTemperature()}°
                    </div>
                    <hr>
                    <div class="city">
                        ${current_weather.city.name}
                    </div>
                </div>
                
            </div>
            
            <div class="dataContainer">
                <div class="data">
                    <h3>${translation.get("city").getValue(language)}</h3>
                    <table>
                        <tr>
                            <td class="tdDescriptor">
                                Name:
                            </td>
                            <td class="tdValue">
                                ${current_weather.city.name}
                            </td>
                        </tr>
                        <tr>
                            <td class="tdDescriptor">
                                ${translation.get("country").getValue(language)}:
                            </td>
                            <td class="tdValue">
                                ${current_weather.city.country}
                            </td>
                        </tr>
                        <tr>
                            <td class="tdDescriptor">
                                ${translation.get("sunrise").getValue(language)}:
                            </td>
                            <td class="tdValue">
                                ${current_weather.city.sun.getRiseFormatted()}
                            </td>
                        </tr>
                        <tr>
                            <td class="tdDescriptor">
                                ${translation.get("sunset").getValue(language)}:
                            </td>
                            <td class="tdValue">
                                ${current_weather.city.sun.getSetFormatted()}
                            </td>
                        </tr>
                    </table>
                </div>
                
                <div class="data">
                    <h3>${translation.get("temperature").getValue(language)}</h3>
                    <table>
                        <tr>
                            <td class="tdDescriptor">
                                ${translation.get("value").getValue(language)}:
                            </td>
                            <td class="tdValue">
                                ${current_weather.temperature.value}
                            </td>
                        </tr>
                        <tr>
                            <td class="tdDescriptor">
                                Min:
                            </td>
                            <td class="tdValue">
                                ${current_weather.temperature.min}
                            </td>
                        </tr>
                        <tr>
                            <td class="tdDescriptor">
                                Max:
                            </td>
                            <td class="tdValue">
                                ${current_weather.temperature.max}
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="data">
                    <h3>${translation.get("wind").getValue(language)}</h3>
                    <table>
                        <tr>
                            <td class="tdDescriptor">
                                ${translation.get("speed").getValue(language)}:
                            </td>
                            <td class="tdValue">
                                ${current_weather.wind.speed.value}${current_weather.wind.speed.unit}
                            </td>
                        </tr>
                        <c:if test="${current_weather.wind.direction.name != null}">
                             <tr>
                                <td class="tdDescriptor">
                                    ${translation.get("direction").getValue(language)}:
                                </td>
                                <td class="tdValue">
                                    ${current_weather.wind.direction.name}
                                </td>
                            </tr>   
                        </c:if>
                    </table>
                </div>
                <div class="data">
                    <h3>${translation.get("atmos").getValue(language)}</h3>
                    <table>
                        <tr>
                            <td class="tdDescriptor">
                                ${translation.get("humidity").getValue(language)}:
                            </td>
                            <td class="tdValue">
                                ${current_weather.humidity.value}${current_weather.humidity.unit}
                            </td>
                        </tr>
                        <tr>
                            <td class="tdDescriptor">
                                ${translation.get("pressure").getValue(language)}:
                            </td>
                            <td class="tdValue">
                                ${current_weather.pressure.value}${current_weather.pressure.unit}
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </c:if>
    </body>
    <script>
        function changeLanguage(){
            let lang = document.getElementById('languageSelector').value;
            document.cookie = "lang="+lang+";"
            location.reload();
        }
    </script>
</html>
