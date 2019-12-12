/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entity.Feed;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.FeedLogic;

/**
 *
 * @author Umang
 */

@WebServlet(name="create feed", urlPatterns ={"/CreateFeed"} )
public class CreateFeed extends HttpServlet {
    public static String errorMessage = null;

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
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
                  out.println("<link rel=\"stylesheet\" href=\"style/feed.css\"");
                      
            out.println("</head>");
            out.println("<body>");
            out.println("<h1 id=\"top\">Servlet CreateFeed </h1>");
            out.println("<div class = \"hostid\" >");
           
    
                         out.println("<form method=\"post\">");
            
                         out.println("Enter Your Host Id: ");
            
            out.printf("\n<input type=\"number\" name=\"%s\" value=\"\"><br>",FeedLogic.HOSTID);
       
            out.println("<br>");
              out.println("Enter PATH : ");
            
            out.printf("\n<input type=\"url\" name=\"%s\" value=\"\"><br>",FeedLogic.PATH);
         
            out.println("<br>");
              out.println("Enter Your Name: ");
            
            out.printf("\n<input type=\"text\" name=\"%s\" value=\"\"><br>",FeedLogic.NAME);
   
              out.println("<br>");
              out.println("Enter Type of Art: ");
            
            out.printf("\n<input type=\"text\" name=\"%s\" value=\"\"><br>",FeedLogic.TYPE);
           
            out.println("<br>");
            out.println("<input type=\"submit\" name=\"view\" value=\"Add and View\">");
            out.println("<input type=\"submit\" name=\"add\" value=\"Add\">");
            out.println("</form>");
              
            if(errorMessage!=null && !errorMessage.isEmpty()){
                out.println("<p color=red>");
                out.println("<font color=red size=4px>");
                out.println(errorMessage);
                out.println("</font>");
                out.println("</p>");
            }
            
             out.println("Submitted keys and values:");
            out.println(toStringMap(request.getParameterMap()));
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    
      private String toStringMap(Map<String, String[]> values) {
        StringBuilder builder = new StringBuilder();
        values.forEach((k, v) -> builder.append("Key=").append(k)
                .append(", ")
                .append("Value/s=").append(Arrays.toString(v))
                .append(System.lineSeparator()));
        return builder.toString();
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
          FeedLogic fLogic = new FeedLogic();

        //grab the parameter NAME first
       
        String path=request.getParameter(FeedLogic.PATH);
        String id = request.getParameter(FeedLogic.HOSTID);
        String type=request.getParameter(FeedLogic.TYPE);
        String name=request.getParameter(FeedLogic.NAME);
        
        
        
        
        //since it is unique we check for duplicates.
       

        
          
     
   
              
         
       try{

           if((path.length() > 0) && (path.length() <= 255)){
            
        if((name.length() > 0) &&  (name.length() <= 45)){
        
        if((type.length() > 0) && (type.length() <= 45)){
           
            if(fLogic.getFeedWithPath(path)==null){
            //if no duplicates create the entity and add it.
            Feed feed = fLogic.createEntity( request.getParameterMap());
            fLogic.add( feed);
       
            
        }else{
                
            errorMessage = "Feed: \"" + path + "\" already exists";
         }
                
               
        
                }else{
                errorMessage= "Type Must have some length greater than 0 and less than 45 CHARACTER";
            
                     }   
       
              }else{
            
                errorMessage= "Name Must have some length greater than 0 and less than 45 CHARACTER";
            
                }
       
               }else{
                errorMessage= "Path Must have some length greater than 0 and less than 255 CHARACTER";
                     }  
            
            
       
       }catch(Exception e){
               errorMessage = "Host: \"" + id + "\" Doesnt exists Create New One";
       }
     
       
       
       
       
        if( request.getParameter("add")!=null){
            //if add button is pressed return the same page
            processRequest(request, response);
            
        }else if (request.getParameter("view")!=null) {
            //if view button is pressed redirect to the appropriate table
            
                
            
            response.sendRedirect("FeedTable");
            
            }
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
