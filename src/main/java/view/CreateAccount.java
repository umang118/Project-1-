/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.AccountLogic;

/**
 *
 * @author Umang
 */
@WebServlet(name="CreateAccount", urlPatterns ={"/CreateAccount"} )
public class CreateAccount extends HttpServlet {

    public static String errorMessage=null;
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
            out.println("<h1 id=\"top\">Servlet CreateAccount </h1>");
            out.println("<div class = \"hostid\" >");
           
    
                         out.println("<form method=\"post\">");
            
                         out.println("Enter Display Name: ");
            
            out.printf("\n<input type=\"text\" name=\"%s\" value=\"\"><br>",AccountLogic.DISPLAY_NAME);
       
            out.println("<br>");
              out.println("Enter User : ");
            
            out.printf("\n<input type=\"text\" name=\"%s\" value=\"\"><br>",AccountLogic.USER);
         
            out.println("<br>");
              out.println("Enter Your Password: ");
            
            out.printf("\n<input type=\"password\" name=\"%s\" value=\"\"><br>",AccountLogic.PASSWORD);
   
              out.println("<br>");

           
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
            
             out.println("Submitted keys and values:\n");
            out.println(toStringMap(request.getParameterMap()));
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    
      private String toStringMap(Map<String, String[]> values) {
        StringBuilder builder = new StringBuilder();
        values.forEach((k, v) -> builder.append("\nKey=").append(k)
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
         
        AccountLogic aLogic = new AccountLogic();

        //grab the parameter NAME first
       
        String displayname=request.getParameter(AccountLogic.DISPLAY_NAME);
        String user = request.getParameter( AccountLogic.USER);
        String pass = request.getParameter(AccountLogic.PASSWORD);
        
        
        if((displayname.length() > 0) && (displayname.length() < 45)){
            
        if((user.length() > 0) &&  (user.length() < 45)){
        
        if((pass.length() > 0) && (pass.length() < 45)){
        
        


//since it is unique we check for duplicates.
         if(aLogic.getAccountWithDisplayName(displayname)==null){
            //if no duplicates create the entity and add it.
            
            if((aLogic.getAccountWithUser(user)== null)){
               
            Account act = aLogic.createEntity( request.getParameterMap());
            aLogic.add( act);
            
            }else{
                errorMessage = "Account: \"" + user + "\" already exists";
            }
           
            
        }else{
            
            if(aLogic.getAccountWithDisplayName(displayname)!=null){
            errorMessage = "Account: \"" + displayname + "\" already exists";
            }
            }
      
     
        
        
        }else{
            errorMessage= "Password Must be in limit must have 1 character or less than 45";
                    
                }
        
        }else{
                        errorMessage= "User must be in limit must have 1 character or less than 45";
                        
                        }
            
        }else{
        
            errorMessage= "Displayname Must be in limit must have 1 character or less than 45";
        
        }
        
         
        
        
        if( request.getParameter("add")!=null){
            //if add button is pressed return the same page
            processRequest(request, response);
            
        }else if (request.getParameter("view")!=null) {
            //if view button is pressed redirect to the appropriate table
            
                
            
            response.sendRedirect("AccountTable");
            
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


