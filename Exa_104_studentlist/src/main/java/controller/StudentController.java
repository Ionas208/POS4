/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bl.Student;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
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
@WebServlet(name = "StudentController", urlPatterns = {"/StudentController"})
public class StudentController extends HttpServlet {
    private List<Student> students = new ArrayList<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config); //To change body of generated methods, choose Tools | Templates.
        String filepath = this.getServletContext().getRealPath("/")+"WEB-INF"+File.separator+"students_2020.csv";
        try{
            InputStream is = new FileInputStream(filepath);
            students = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))
                    .lines()
                    .skip(1)
                    .map(Student::new)
                    .collect(Collectors.toList());
        }
        catch(FileNotFoundException e){
            System.out.println("File {"+filepath+"} not found");
        }
        
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
        request.getRequestDispatcher("studentView.jsp").forward(request, response);
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
        request.setCharacterEncoding("UTF-8");
        List<Student> tempList = new ArrayList<>();
            tempList.addAll(students);
        request.setAttribute("students", tempList);
        request.setAttribute("selectedStudent", students.get(0));
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
        request.setCharacterEncoding("UTF-8");
        String filter = request.getParameter("filter");
        request.setAttribute("filter", filter == null ? "" : filter);
        if(filter == null || filter.equals("")){
            List<Student> tempList = new ArrayList<>();
            tempList.addAll(students);
            request.setAttribute("students", tempList);
        }
        else{
            List<Student> filtered = new ArrayList<>();
            filtered.addAll(students);
            filtered.removeIf(s -> !((s.getLastname()+" "+s.getFirstname()).toUpperCase()).contains(filter.toUpperCase()));
            request.setAttribute("students", filtered);
        }
        
        try{
            Student selectedStudent = null;
            String catNrAndClass = request.getParameter("catNr;class") == null ? "" : request.getParameter("catNr;class");
            String[] parts = catNrAndClass.split(";");
            String classname = "";
            int catNr = 0;
                    
            try{
                classname = parts[1];
                catNr = Integer.parseInt(parts[0]);
            }
            catch(ArrayIndexOutOfBoundsException e){
            }
            
            for(Student s: students){
                if(s.getCatNr() == catNr && s.getClassname().equals(classname)){
                    selectedStudent = s;
                    break;
                }
            }
            request.setAttribute("selectedStudent", selectedStudent);
        }
        catch(NumberFormatException e){
        }
        
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
