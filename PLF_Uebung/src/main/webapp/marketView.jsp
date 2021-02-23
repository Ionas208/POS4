<%-- 
    Document   : marketView
    Created on : 23.02.2021, 14:29:01
    Author     : 10jon
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Grocery Market</title>
    </head>
    <body>
        <h1>Grocery Market</h1>
        <form action="./MarketController" method="POST">
            <select name="add">
                <c:forEach items="${JSONProducts}" var="product">
                    <option value="${product.name};${product.price}">${product.name} ${product.price}€</option>
                </c:forEach>
            </select>
            <select name="num">
                <c:forEach begin="1" end="5" var="i">
                    <option value="${i}">${i}</option>
                </c:forEach>
            </select>
            <input type="submit" value="add">
        </form>
        
        
        <h2>Shopping Cart</h2>
        <c:if test="${orders != null}">
            <form action="./MarketController" method="POST">
                <table>
                    <thead>
                        <th>article</th>
                        <th>quantity</th>
                        <th>price</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${orders}" var="order">
                            <tr>
                                <td>${order.product.name}</td>
                                <td>${order.quantity}</td>
                                <td>€ ${order.product.price}</td>
                                <td>
                                    <input name="remove" value="${order.product.name};${order.product.price}" style="display: none">
                                    <input type="submit" value="remove">
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </form>
        </c:if>
        
    </body>
</html>
