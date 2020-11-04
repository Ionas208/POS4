/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import beans.Lesson;
import beans.Weekday;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
@WebServlet(name = "SubstitudeController", urlPatterns = {"/SubstitudeController"})
public class SubstitudeController extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SubstitudePlan sp = new SubstitudePlan();
        List<String> teachers = new ArrayList<>();
        teachers.add("SF");
        teachers.add("LB");
        
        
        sp.addLesson(Weekday.MON, new Lesson("POS", teachers, false));
        sp.addLesson(Weekday.MON, new Lesson("POS", teachers, false));
        sp.addLesson(Weekday.MON, new Lesson("POS", teachers, false));
        
        sp.addLesson(Weekday.TUE, new Lesson("POS", teachers, false));
        sp.addLesson(Weekday.TUE, new Lesson("POS", teachers, false));
        sp.addLesson(Weekday.TUE, new Lesson("POS", teachers, false));
        
        sp.addLesson(Weekday.WED, new Lesson("POS", teachers, false));
        sp.addLesson(Weekday.WED, new Lesson("POS", teachers, false));
        sp.addLesson(Weekday.WED, new Lesson("POS", teachers, false));
        
        sp.addLesson(Weekday.THU, new Lesson("POS", teachers, false));
        sp.addLesson(Weekday.THU, new Lesson("POS", teachers, false));
        sp.addLesson(Weekday.THU, new Lesson("POS", teachers, false));
        
        sp.addLesson(Weekday.FRI, new Lesson("POS", teachers, false));
        sp.addLesson(Weekday.FRI, new Lesson("POS", teachers, false));
        sp.addLesson(Weekday.FRI, new Lesson("POS", teachers, false));
        
        
        config.getServletContext().setAttribute("timetable", sp);
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
        request.getRequestDispatcher("substitudePlanView.jsp").forward(request, response);
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
         request.getRequestDispatcher("substitudePlanView.jsp").forward(request, response);
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
