/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.Language;
import beans.Pizza;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jdk.nashorn.api.tree.ArrayLiteralTree;

/**
 *
 * @author 10jon
 */
@WebServlet(name = "PizzaOrderServlet", urlPatterns = {"/PizzaOrderServlet"})
public class PizzaOrderServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        List<Pizza> pizzas = new ArrayList<>();
        
        Map<Language, String> translation = new HashMap<>();
        translation.put(Language.EN, "Cheese and Tomatoes");
        translation.put(Language.DE, "Käse und Tomatensauce");
        pizzas.add(new Pizza("Magaritha", translation, 6.5, "https://www.kuechengoetter.de/uploads/media/1800x1200/02/4282-pizza-margherita.jpg?v=1-0"));
        
        translation = new HashMap<>();
        translation.put(Language.EN, "Cheese, Tomatoes and Salami");
        translation.put(Language.DE, "Käse, Tomatensauce und Salami");
        pizzas.add(new Pizza("Salami", translation, 7, "https://ichkocheheute.de/wp-content/uploads/2017/08/P1120511.jpg"));
        
        translation = new HashMap<>();
        translation.put(Language.EN, "Cheese, Tomatoes and Ham");
        translation.put(Language.DE, "Käse, Tomatensauce und Schinken");
        pizzas.add(new Pizza("Proscuitto", translation, 7, "https://www.gutekueche.ch/upload/rezept/546/1600x900_pizza-prosciutto.jpg"));
        
        translation = new HashMap<>();
        translation.put(Language.EN, "Cheese, Tomatoes and Rucola");
        translation.put(Language.DE, "Käse, Tomatensauce und Rucola");
        pizzas.add(new Pizza("Rucola", translation, 12, "https://www.katha-kocht.de/wp-content/uploads/2012/07/Chorizo-Rucola-Pizza-2-1024x683.jpg"));
        
        translation = new HashMap<>();
        translation.put(Language.EN, "Cheese, Tomatoes, Ham and Pineapple");
        translation.put(Language.DE, "Käse, Tomatensauce, Schinken und Ananas");
        pizzas.add(new Pizza("Hawaii", translation, 8, "https://image.kurier.at/images/cfs_landscape_616w_347h/1403760/46-90332057.jpg"));
        config.getServletContext().setAttribute("pizzas", pizzas);
    }
    
    

    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie c = new Cookie("test", "yeet");
        c.setMaxAge(60*60);
        response.addCookie(c);
        request.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("PizzaOrder.jsp").forward(request, response);  
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Map<Pizza, Integer> pizza_order = new HashMap<>();
        List<Pizza> pizzas = (List<Pizza>)request.getServletContext().getAttribute("pizzas");
        boolean hasPizza = false;
        for(Pizza p : pizzas){
            int num = 0;
            try{
                num = Integer.parseInt(request.getParameter("number_"+p.getName()));
            }
            catch(NumberFormatException ex){
            }
            
            if(num>0){
                hasPizza=true;
            }
            pizza_order.put(p, num);
        }
        if(!hasPizza || request.getParameter("address").equals("")){
            if(!hasPizza){
                request.setAttribute("pizzaError", "No Pizza selected");
            }
            if(request.getParameter("address").equals("")){
                request.setAttribute("addressError", "No Address entered");
            }
            request.getRequestDispatcher("PizzaOrder.jsp").forward(request, response);
        }
        request.setAttribute("pizza_order", pizza_order);
        request.getRequestDispatcher("PizzaOrderSummary.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
