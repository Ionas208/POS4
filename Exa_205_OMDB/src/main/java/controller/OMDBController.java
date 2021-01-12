/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pojos.Movie;

/**
 *
 * @author 10jon
 */
@WebServlet(name = "OMDBController", urlPatterns = {"/OMDBController"})
public class OMDBController extends HttpServlet {
   
    private String apiKey = "682c7af3";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("OMDBview.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String id = request.getParameter("id");
        if(id == null){
            request.setAttribute("page", 1);
            request.setAttribute("sort", "");
            request.setAttribute("sort_order", "ASC");
            
            response.setContentType("text/html;charset=UTF-8");
            request.getRequestDispatcher("OMDBview.jsp").forward(request, response);
        }
        else{
            Movie m = getMovie(id);
            request.setAttribute("movie", m);
            response.setContentType("text/html;charset=UTF-8");
            request.getRequestDispatcher("OMDBDetailView.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            String search = request.getParameter("search");
            request.setAttribute("search", search);
            search = search.replace(" ", "%20");

            //Pagination
            int totalResults = getTotalResults(search);
            int pages = (totalResults/10)+1;

            String pageString = request.getParameter("p");
            int page = 1;
            if(pageString != null){
                page = Integer.parseInt(pageString);
            }

            if(page<=0){
                page = 1;
            }
            else if(page>pages){
                page = pages;
            }

            List<Movie> movies = getMoviesPerPage(search, page);
            Set<String> genres = new HashSet<>();
            for (Movie m : movies) {
                String[] tokens = m.getGenre().split(", ");
                genres.addAll(Arrays.asList(tokens));
            }

            String sort = request.getParameter("sort");
            String sort_order = request.getParameter("sort_order");
            if(sort_order == null){
                sort_order = "ASC";
            }

            String genre = request.getParameter("genre");
            if(!genre.equals("")){
                movies.removeIf(m -> !m.getGenre().contains(genre));
            }

            if(!sort.equals("")){
                Comparator<Movie> c = ((m1,m2)->0);
                boolean reversed = sort_order.equals("ASC") ? false : true;
                switch(sort){
                    case "title":
                        c = ((m1, m2) -> m1.getTitle().toUpperCase().compareTo(m2.getTitle().toUpperCase())); 
                        break;
                    case "date":
                        c = ((m1, m2) -> m1.getReleased().compareTo(m2.getReleased()));
                        break;
                }
                if(reversed){
                    c = c.reversed();
                }
                movies.sort(c);
            }


            request.setAttribute("sort", sort);
            request.setAttribute("sort_order", sort_order);
            request.setAttribute("genre", genre);
            request.setAttribute("genres", genres);
            request.setAttribute("movies", movies);
            request.setAttribute("pages", pages);
            request.setAttribute("page", page);

            processRequest(request, response);
        }
        catch(NullPointerException ex){
            System.out.println("*****************************************************");
            response.setContentType("text/html;charset=UTF-8");
            request.getRequestDispatcher("OMDBview.jsp").forward(request, response);
        }
        
    }
    
    
    private int getTotalResults(String search) throws MalformedURLException, IOException{
        JsonMapper mapper = new JsonMapper();
        URL url = new URL("http://www.omdbapi.com/?s="+search+"&apikey="+apiKey);
        JsonNode node = mapper.readTree(url);
        return Integer.parseInt(node.get("totalResults").asText());
    }
    
    private List<Movie> getMoviesPerPage(String search, int page) throws MalformedURLException, IOException{
        JsonMapper mapper = new JsonMapper();
        URL url = new URL("http://www.omdbapi.com/?s="+search+"&page="+page+"&apikey="+apiKey);
        JsonNode node = mapper.readTree(url);
        JavaType type = mapper.getTypeFactory().constructCollectionLikeType(List.class, Movie.class);
        List<Movie> search_movies = mapper.readValue(node.get("Search").toString(), type);
        List<Movie> movies = new ArrayList<>();
        for (Movie movie : search_movies) {
            movies.add(getFullMovie(movie));
        }
        return movies;
    }
    
    private Movie getFullMovie(Movie movie) throws MalformedURLException, IOException{
        JsonMapper mapper = new JsonMapper();
        URL url = new URL("http://www.omdbapi.com/?apikey="+apiKey+"&i="+movie.getImdbID());
        JsonNode node = mapper.readTree(url);
        Movie m = mapper.readValue(node.toString(), Movie.class);
        return m;
    }
    
    private Movie getMovie(String id) throws JsonProcessingException, IOException{
         JsonMapper mapper = new JsonMapper();
        URL url = new URL("http://www.omdbapi.com/?apikey="+apiKey+"&i="+id);
        JsonNode node = mapper.readTree(url);
        Movie m = mapper.readValue(node.toString(), Movie.class);
        return m;
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
