/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import beans.Lesson;
import beans.Weekday;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
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

    private SubstitudePlan sp;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        sp = new SubstitudePlan();
        String filepath = config.getServletContext().getRealPath("/res/timetable.csv");
        sp.loadTimetable(filepath);
        config.getServletContext().setAttribute("timetable", sp);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("substitudePlanView.jsp").forward(request, response);
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
        request.setAttribute("subject_error", "none");
        request.setAttribute("teachers_error", "none");
        request.setAttribute("other_error", "none");
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
        String subject_error = "none";
        String teachers_error = "none";
        String other_error = "none";
        try{
            String subject = request.getParameter("subject");
            if(subject == null ||subject.equals("")){
                subject_error = "inline";
            }

            String teachersString = request.getParameter("teachers");
            if(teachersString == null ||teachersString.equals("")){
                teachers_error = "inline";
            }

            List<String> teachers = new ArrayList<>();
            for (String teacher : teachersString.split(",")) {
                if(teacher.equals("")){
                    teachers_error = "inline";
                }
                teachers.add(teacher.replace(" ", ""));
            }

            Weekday weekday = Weekday.valueOf(request.getParameter("weekday"));
            int hour = Integer.parseInt(request.getParameter("hour"));
            if(hour<1 || hour>10){
                other_error = "inline";
            }
            
            if(!subject_error.equals("inline") && !teachers_error.equals("inline") && !other_error.equals("inline")){
                Lesson l = sp.getLesson(weekday, hour);
                l.setSubject(subject);
                l.setTeachers(teachers);
                l.setSubstitude(true);
            }
            
        }
        catch(Exception ex){
            other_error = "inline";
        }
        
        request.setAttribute("subject_error", subject_error);
        request.setAttribute("teachers_error", teachers_error);
        request.setAttribute("other_error", other_error);
        
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
