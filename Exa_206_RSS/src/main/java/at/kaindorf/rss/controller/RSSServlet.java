package at.kaindorf.rss.controller;

import at.kaindorf.rss.io.XMLHandler;
import at.kaindorf.rss.pojos.Channel;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "RSSServlet", urlPatterns = {"/RSSServlet"})
public class RSSServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("RSSView.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Channel> channels = (List<Channel>)request.getSession().getAttribute("channels");
        if(channels == null){
            channels = new ArrayList<>();
        }
        request.getSession().setAttribute("channels", channels);
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String rss = request.getParameter("rss");
        
        List<Channel> channels = (List<Channel>)request.getSession().getAttribute("channels");
        if(channels == null){
            channels = new ArrayList<>();
        }
        
        if(rss != null){
            Channel c = XMLHandler.getChannel(rss);
            channels.add(c);
        }
        System.out.println(channels);
        request.getSession().setAttribute("channels", channels);
        processRequest(request, response);
    }
}
