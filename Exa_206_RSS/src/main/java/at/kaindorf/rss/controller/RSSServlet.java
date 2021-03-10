package at.kaindorf.rss.controller;

import at.kaindorf.rss.io.XMLHandler;
import at.kaindorf.rss.pojos.Channel;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "RSSServlet", urlPatterns = {"/RSSServlet"})
public class RSSServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        String feeds_path = config.getServletContext().getRealPath("/res/default_feeds.csv");
        try {
            List<String> feed_urls = Files.lines(Paths.get(feeds_path)).skip(1).map(s -> s).collect(Collectors.toList());
            List<Channel> default_channels = new ArrayList<>();
            for (String feed : feed_urls) {
                default_channels.add(XMLHandler.getChannel(feed));
            }
            config.getServletContext().setAttribute("default_channels", default_channels);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
                                        
    }

    
    
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
