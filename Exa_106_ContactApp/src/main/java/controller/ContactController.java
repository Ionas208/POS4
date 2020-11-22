/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.Filter;
import beans.Sorter;
import bl.ContactListModel;
import io.IO_Helper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.PatternSyntaxException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pojos.Contact;

/**
 *
 * @author 10jon
 */
@WebServlet(name = "ContactController", urlPatterns = {"/ContactController"})
public class ContactController extends HttpServlet {

    private ContactListModel clm;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        List<Contact> contacts = IO_Helper.getContacts(config.getServletContext().getRealPath("/res/contacts.json"));
        clm = new ContactListModel(contacts);
        config.getServletContext().setAttribute("clm", clm);
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
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("contactView.jsp").forward(request, response);
    }

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
        processRequest(request, response);
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
        
        List<String> errors = new ArrayList<>();
        
        try{
            List<Integer> ids_to_delete = new ArrayList<>();
            Enumeration<String> params = request.getParameterNames();
            while(params.hasMoreElements()){
                String param = params.nextElement();
                try{
                    int num = Integer.parseInt(param);
                    ids_to_delete.add(num);
                }
                catch(NumberFormatException ex){
                }
            }
            clm.delete(ids_to_delete);
            
            Filter filterby = Filter.valueOf(request.getParameter("filterby"));
            String filter = request.getParameter("filter");

            Map<Sorter, Boolean> sorters = new LinkedHashMap<>();
            for (int i = 1; i <= 3; i++) {
                Sorter option = Sorter.valueOf(request.getParameter("option"+i));
                String option_order_string = request.getParameter("option"+i+"_order");
                Boolean option_order = !option_order_string.equals("ASC"); 

                if(!(option == Sorter.NONE) && !sorters.containsKey(option)){
                    sorters.put(option, option_order);
                }  
            }

            clm.sort(sorters);
            try{
                clm.filter(filterby, filter);
            }
            catch(PatternSyntaxException ex){
                System.out.println(ex);
                errors.add("Illegal regex");
            }
            
            
        }
        catch(IllegalArgumentException ex){
            System.out.println(ex);
            errors.add("Illegal Filter or Sorting Option");
        }
        
        request.setAttribute("errors", errors);
        
        processRequest(request, response);
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
