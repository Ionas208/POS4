/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.owm.servlet;

import at.kaindorf.owm.IO.IOHandler;
import at.kaindorf.owm.beans.Language;
import at.kaindorf.owm.bl.LanguageHandler;
import at.kaindorf.owm.bl.RequestHandler;
import at.kaindorf.owm.pojos.weather.Current;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 10jon
 */
@WebServlet(name = "OWMController", urlPatterns = {"/OWMController"})
public class OWMController extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        config.getServletContext().setAttribute("translation", IOHandler.getTranslations(config.getServletContext().getRealPath("/WEB-INF/translations.xml")));
        config.getServletContext().setAttribute("languages", Arrays.asList(Language.values()));
    }

    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("OWMView.jsp").forward(request, response);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        setLanguage(request, response);
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        setLanguage(request, response);
        String search = request.getParameter("search");
        Language l = (Language)request.getAttribute("language");
        Current c = RequestHandler.getCurrentWeather(search, l);
        request.setAttribute("current_weather", c);
        processRequest(request, response);
    }
    
    private void setLanguage(HttpServletRequest request, HttpServletResponse response){
        Language l = LanguageHandler.getLanguage(request, response);
        request.setAttribute("language", l);
    }
}
