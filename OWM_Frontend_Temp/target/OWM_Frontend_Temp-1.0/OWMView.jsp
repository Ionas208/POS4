<%-- 
    Document   : OWMView
    Created on : 16.02.2021, 09:46:09
    Author     : 10jon
--%>

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
                    <input type="text" name="search" placeholder="City name">
                    <button onclick="submit();"><i class="fas fa-search"></i></button>
                </form>
            </div>
            
        </div>
        
        
        <div class="container">
            <div class="summary">
                <img src="http://openweathermap.org/img/wn/10d@2x.png" width="200px%" height="200px%"><br>
                
                <div>
                    <div class="degrees">
                        -2°
                    </div>
                    <hr>
                    <div class="city">
                        Perlsdorf
                    </div>
                </div>
                
            </div>
            
            <div class="dataContainer">
                <div class="data">
                    <h3>City</h3>
                    <table>
                        <tr>
                            <td class="tdDescriptor">
                                Name:
                            </td>
                            <td class="tdValue">
                                Perlsdorf
                            </td>
                        </tr>
                        <tr>
                            <td class="tdDescriptor">
                                Country:
                            </td>
                            <td class="tdValue">
                                AT
                            </td>
                        </tr>
                        <tr>
                            <td class="tdDescriptor">
                                Sunrise:
                            </td>
                            <td class="tdValue">
                                time
                            </td>
                        </tr>
                        <tr>
                            <td class="tdDescriptor">
                                Sunset:
                            </td>
                            <td class="tdValue">
                                time
                            </td>
                        </tr>
                    </table>
                </div>
                
                <div class="data">
                    <h3>Temperature</h3>
                    <table>
                        <tr>
                            <td class="tdDescriptor">
                                Value:
                            </td>
                            <td class="tdValue">
                                -2°C
                            </td>
                        </tr>
                        <tr>
                            <td class="tdDescriptor">
                                Min:
                            </td>
                            <td class="tdValue">
                                -5°C
                            </td>
                        </tr>
                        <tr>
                            <td class="tdDescriptor">
                                Max:
                            </td>
                            <td class="tdValue">
                                0°C
                            </td>
                        </tr>
                        <tr>
                            <td class="tdDescriptor">
                                Feels like:
                            </td>
                            <td class="tdValue">
                                -2°C
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="data">
                    <h3>Wind</h3>
                    <table>
                        <tr>
                            <td class="tdDescriptor">
                                Speed:
                            </td>
                            <td class="tdValue">
                                Calm, 0.32m/s
                            </td>
                        </tr>
                        <tr>
                            <td class="tdDescriptor">
                                Direction:
                            </td>
                            <td class="tdValue">
                                South-East
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="data">
                    <h3>Atmospheric Data</h3>
                    <table>
                        <tr>
                            <td class="tdDescriptor">
                                Humidity:
                            </td>
                            <td class="tdValue">
                                30%
                            </td>
                        </tr>
                        <tr>
                            <td class="tdDescriptor">
                                Pressure:
                            </td>
                            <td class="tdValue">
                                1024hPa
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            
        </div>
    </body>
</html>
