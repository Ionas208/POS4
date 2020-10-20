<%-- 
    Document   : PizzaOrder
    Created on : 07.10.2020, 11:25:14
    Author     : 10jon
--%>

<%@page import="java.util.Arrays"%>
<%@page import="beans.Language"%>
<%@page import="beans.LanguageSelect"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>
<%@page import="beans.Pizza"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="pizza_style.css">
        <link rel="stylesheet" href="pizza_order_style.css">
        <script src="https://kit.fontawesome.com/dd041cd8d6.js" crossorigin="anonymous"></script>
        <script src="pizza_js.js"></script>
        <link href="https://fonts.googleapis.com/css2?family=Libre+Baskerville&family=Pinyon+Script&display=swap" rel="stylesheet">
    </head>
    <body>
        <select name="language" id="language_selector" onchange="changeLanguage()">
            <%
                Language lang = LanguageSelect.getLanguage(request, response);
                out.println("<option value='"+lang.getLanguage_code()+"'>"+lang.getLanguage_name()+"</option>");
                List<Language> languages = Arrays.asList(Language.values());
                for(Language l : languages){
                    if(l == lang) continue;
                    out.println("<option value='"+l.getLanguage_code()+"'>"+l.getLanguage_name()+"</option>");
                }
            %>
        </select>
        <h1><i class="fas fa-pizza-slice"></i><span>Pizzeria di Metro</span></h1>
        <%
            String pizzaError = (String)request.getAttribute("pizzaError");
            String addressError = (String)request.getAttribute("addressError");
            if(pizzaError != null){
                out.println("<div class='xerror'>"+pizzaError+"</div>");
            }
            if(addressError != null){
                out.println("<div class='xerror'>"+addressError+"</div>");
            }
        %>
        <form id="pizza_form" action="/Exa_105_Pizzeria/PizzaOrderServlet" method="post" onsubmit="return validate()">
            <table>
                <%
                    List<Pizza> pizzas = (List<Pizza>)application.getAttribute("pizzas");
                    for(Pizza p : pizzas){
                        out.println("<tr>");
                        
                        out.println("<td><img width='150px' height='100px' src='"+p.getImg_url()+"'></td>");

                        out.println("<td class='pizza_text'>");
                        out.println("<div class='pizza_name'>"+p.getName()+"</div>");
                        out.println("<div class='pizza_description'>"+p.getDescription().get(lang)+"</div>");
                        out.println("</td>");
                        
                        out.println("<td>"+p.getPrice()+"€</td>");
                        
                        out.println("<td><input class='number_input' min='0' value='0' max='10' name='number_"+p.getName()+"' type='number'></td>");
                        
                        out.println("</tr>");
                    }
                %>
            </table>
            <div>
                <label><%
                    if(lang == Language.DE){
                        out.println("Lieferadresse: ");
                    }
                    else if(lang == Language.EN){
                        out.println("Delivery Address: ");
                    }
                    %></label><br>
                <input type="text" name="address" autocomplete='off' id="address_input"><br>
                <button><%
                    if(lang == Language.DE){
                        out.println("Bestellen");
                    }
                    else if(lang == Language.EN){
                        out.println("Order");
                    }
                    %></button>
        </form>
    </body>
</html>
<script>
    function changeLanguage(){
        let lang = document.getElementById('language_selector').value;
        document.cookie = "lang="+lang+";"
        location.reload();
    }
</script>
