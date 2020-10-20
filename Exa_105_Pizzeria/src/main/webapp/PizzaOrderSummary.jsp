<%-- 
    Document   : PizzaOrderSummary
    Created on : 07.10.2020, 11:25:14
    Author     : 10jon
--%>

<%@page import="java.util.LinkedList"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.ArrayList"%>
<%@page import="beans.LanguageSelect"%>
<%@page import="beans.Language"%>
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
        <link rel="stylesheet" href="pizza_summary_style.css">
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
        <table>
            <thead>
                <th>Pizza</th>
                <th><%
                    if(lang == Language.DE){
                        out.println("Preis");
                    }
                    else if(lang == Language.EN){
                        out.println("Price");
                    }
                    %></th>
                <th><%
                    if(lang == Language.DE){
                        out.println("Anzahl");
                    }
                    else if(lang == Language.EN){
                        out.println("Number");
                    }
                    %></th>
                <th><%
                    if(lang == Language.DE){
                        out.println("Gesamt");
                    }
                    else if(lang == Language.EN){
                        out.println("Total");
                    }
                    %></th>
            </thead>
            <tbody>
                <%
                    
                    List<Pizza> pizzas = (List<Pizza>)application.getAttribute("pizzas");
                    double sum = 0;
                    for(Pizza p : pizzas){
                        int num = 0;
                        try{
                            num = Integer.parseInt(request.getParameter("number_"+p.getName()));
                        }
                        catch(NumberFormatException e){
                        }
                        out.println("<tr>");
                        
                        out.println("<td class='alignLeft'>");
                        out.println(p.getName());
                        out.println("</td>");
                        
                        out.println("<td class='alignRight'>");
                        out.println(p.getPrice()+"€");
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(num);
                        out.println("</td>");
                        
                        out.println("<td class='alignRight'>");
                        out.println((p.getPrice()*num)+"€");
                        out.println("</td>");
                        sum += p.getPrice()*num;
                        
                        out.println("</tr>");
                    }
                %>
            </tbody>
            <tfoot>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td class='alignRight' id='sum'><% out.println(sum); %>€</td>
                </tr>
            </tfoot>
        </table>
        <div>
            <span>
            <%
                if(lang == Language.DE){
                    out.println("Adresse: ");
                }
                else if(lang == Language.EN){
                    out.println("Address: ");
                }
                out.println(request.getParameter("address"));
            %>
            </span>
        </div>
        <button onclick="goBack()"><%
                    if(lang == Language.DE){
                        out.println("Zurück");
                    }
                    else if(lang == Language.EN){
                        out.println("Back");
                    }
                    %></button>
    </body>
</html>
<script>
    function goBack() {
        location.replace("/Exa_105_Pizzeria/");
        //window.history.back();
    }
    
    function changeLanguage(){
        let lang = document.getElementById('language_selector').value;
        document.cookie = "lang="+lang+";"
        location.reload();
    }
</script>
