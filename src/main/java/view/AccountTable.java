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
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.AccountLogic;

/**
 *
 * @author Umang
 */
 
public class AccountTable extends HttpServlet {

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
            out.println("<title>Account Table</title>");            
            out.println("</head>");
            out.println("<body>");
            
            AccountLogic logic = new AccountLogic();
            List<Account> entity = logic.getAll();
            out.println("<table align=\"center\" border=\"1\">");
            out.println("<h1>Account Table</h1>");
            //this is an example for your other table use getColumnNames from logic to 
            //create headers in a loop.
            out.println("<tr>");
            out.println("<th>ID</th>");
          
            out.println("<th>Display_NAME</th>");
            out.println("<th>User</th>");
            out.println("<th>Password</th>");
            out.println("</tr>");
            for (Account e : entity) {
                //for other tables replace the code bellow and use extractDataAsList
                //in a loop to fill the data.
                out.printf("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>\n",
                        e.getId(),e.getDisplayName(),e.getUser(),e.getPassword());
            }
            out.println("<tr>");
            //this is an example for your other table use getColumnNames from logic to 
            //create headers in a loop.
                     out.println("<th>ID</th>");
          
            out.println("<th>Display_NAME</th>");
            out.println("<th>User</th>");
            out.println("<th>Password</th>");
            out.println("</tr>");
            out.println("</table>");
            out.printf("<div style=\"text-align: center;\"><pre>%s</pre></div>", toStringMap(request.getParameterMap()));
            out.println("</body>");
            out.println("</html>");
        
            out.println("</html>");
        }
    }
    
    
    
    
     private String toStringMap(Map<String, String[]> m) {
        StringBuilder builder = new StringBuilder();
        for(String k: m.keySet()) {
            builder.append("Key=").append(k)
                    .append(", ")
                    .append("Value/s=").append(Arrays.toString(m.get(k)))
                    .append(System.lineSeparator());
        }
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
