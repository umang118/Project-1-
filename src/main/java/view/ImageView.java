/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import common.FileUtility;
import entity.Feed;
import entity.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.ImageLogic;
import scraper.Post;
import scraper.Scraper;
import scraper.Sort;

/**
 *
 * @author Umang
 */
public class ImageView extends HttpServlet {
    
   

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
               out.println("<link rel=\"stylesheet\" href=\"style/ImageView.css\"");
               
            
            out.println("<title>Servlet ImageView</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"imageContainer\">");
            ImageLogic ilogic =new ImageLogic();
      List<Image> entity = ilogic.getAll();
    
          
            out.println("<h1>Image</h1>");
            
            //this is an example for your other table use getColumnNames from logic to 
            //create headers in a loop.
          
      
                //for other tables replace the code bellow and use extractDataAsList
                //in a loop to fill the data.
             for(Image e : entity){
                 
            out.printf("<img src=\"%s\" class=\"imageThumb\">", "image/"+FileUtility.getFileName(e.getPath()));
             
             }
 
      
  
            
            out.println("</div>");
            out.println("</body>");
            
            out.println("</html>");
        }
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
        
  
         
        FileUtility.createDirectory(System.getProperty("user.home")+"/My Documents/Reddit Images/");

        //create a lambda that accepts post
        Consumer<Post> saveImage = (Post post) -> {
            //if post is an image and SFW
            if (post.isImage() && !post.isOver18()) {
                //get the path for the image which is unique
                String path = post.getUrl();
                //save it in img directory
               
                FileUtility.downloadAndSaveFile(path,System.getProperty("user.home")+"/My Documents/Reddit Images/");
                
               ImageLogic iLogic = new ImageLogic();
                Image image =new Image();
                
                image.setName(post.getTitle());
                image.setPath(post.getUrl());
                image.setDate(post.getDate());
               image.setFeedid(new Feed(1));
                
                
                if(iLogic.search(image.getPath()).size()<1) {
                  
                    iLogic.add(image);
                    
                }
                
            }
        };

//        //create a new scraper
        Scraper scrap = new Scraper();
        //authenticate and set up a page for wallpaper subreddit with 5 posts soreted by HOT order
        scrap.authenticate().buildRedditPagesConfig("Wallpaper", 5, Sort.HOT);
        //get the next page 3 times and save the images.
        scrap.requestNextPage().proccessNextPage(saveImage);
        
     

        
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
    
    
    
    
      private String toStringMap(Map<String, String[]> values) {
        StringBuilder builder = new StringBuilder();
        values.forEach((k, v) -> builder.append("Key=").append(k)
                .append(", ")
                .append("Value/s=").append(Arrays.toString(v))
                .append(System.lineSeparator()));
        return builder.toString();

}
}
