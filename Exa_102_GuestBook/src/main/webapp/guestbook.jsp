<%-- 
    Document   : guestbook
    Created on : 24.09.2020, 08:41:13
    Author     : 10jon
--%>

<%@page import="java.util.List"%>
<%@page import="at.kaindorf.beans.GuestbookEntry"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Guestbook</title>
    </head>
    <body style="background-color: #00FFCB">
        <h1>Welcome to our guestbook</h1>
        <form action="GuestbookController" method="POST">
            <table border="0">
                <tbody>
                    <tr>
                        <td>Nickname</td>
                        <td><input type="text" name="nickname" value="Spiderman"></td>
                    </tr>
                    <tr>
                        <td>E-Mail</td>
                        <td><input type="text" name="email" value="spider@man.com"></td>
                    </tr>
                    <tr>
                        <td>Comment</td>
                        <td><textarea name="comment" rows="4" cols="25">
                            My spider senses are tingling
                            </textarea></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="submit"></td>
                    </tr>
                </tbody>
            </table>

        </form>
        <br>
        <hr>
        <br>
        <% 
            List<GuestbookEntry> entries = (List<GuestbookEntry>)request.getAttribute("entries");
            if(entries == null){
               out.println("No entries");
            }
            else{
                for(GuestbookEntry e : entries){
                    out.println(e);
                    out.println("<br>");
                } 
            }
            
        %>
    </body>
</html>
