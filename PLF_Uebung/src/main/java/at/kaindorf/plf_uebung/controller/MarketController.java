/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.plf_uebung.controller;

import at.kaindorf.plf_uebung.beans.Order;
import at.kaindorf.plf_uebung.beans.Product;
import at.kaindorf.plf_uebung.io.json.JSONAccess;
import at.kaindorf.plf_uebung.io.xml.XMLAccess;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "MarketController", urlPatterns = {"/MarketController"})
public class MarketController extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        //XML
        String XMLPath = config.getServletContext().getRealPath("/products.xml");
        List<Product> XMLProducts = XMLAccess.getProducts(XMLPath);
        config.getServletContext().setAttribute("XMLProducts", XMLProducts);
        System.out.println(XMLProducts);
        //JSON
        try {
            String JSONPath = config.getServletContext().getRealPath("/products.json");
            List<Product> JSONProducts = JSONAccess.getProducts(JSONPath);
            
            config.getServletContext().setAttribute("JSONProducts", JSONProducts);
            System.out.println(JSONProducts);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("marketView.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String add = request.getParameter("add");
        String remove = request.getParameter("remove");
        if(add != null){
            String name = add.split(";")[0];
            String price = add.split(";")[1];
            Product p = new Product(name, price, null);
            
            int quantity = Integer.parseInt(request.getParameter("num"));
            
            List<Order> orders = (List<Order>)request.getSession().getAttribute("orders");
            if(orders == null){
                orders = new ArrayList<>();
            }
            
            Order o = getOrder(orders, p);
            if(o == null){
                o = new Order(p, quantity);
            }else{
                orders.remove(o);
                o.setQuantity(o.getQuantity()+quantity);
            }
            orders.add(o);
            request.getSession().setAttribute("orders", orders);
        }
        else if(remove != null){
            String name = remove.split(";")[0];
            String price = remove.split(";")[1];
            Product p = new Product(name, price, null);
            
            List<Order> orders = (List<Order>)request.getSession().getAttribute("orders");
            Order o = getOrder(orders, p);
            orders.remove(o);
            request.getSession().setAttribute("orders", orders);
        }
        
        processRequest(request, response);
    }

    private Order getOrder(List<Order> orders, Product p){
        for (Order order : orders) {
            if(order.getProduct().equals(p)){
                return order;
            }
        }
        return null;
    }

}
