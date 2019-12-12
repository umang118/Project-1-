/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;


import dao.ImageDAO;
import entity.Feed;
import entity.Image;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Umang
 */
public  class ImageLogic extends GenericLogic<Image, ImageDAO> {
    
  public static final String ID = "id";
  public static final String PATH = "path";
  public static final String NAME = "name";
  public static final String DATE = "date";
  public static final String FEEDID = "feedid";
    

    public ImageLogic() {
        super(new ImageDAO());
    }
    
        @Override
    public List<Image> getAll() {
      return get(() -> dao().findAll());
    
    
    }

        @Override
    public Image getWithId(int id) {
   
        return get(()->dao().findById(id));
        
    }
    
    public List<Image> getImagesWithFeedid(int feedid){
        return get(()->dao().findByFeedid(feedid));
    }
    
 public List<Image> getImagesWithName(String name){
     return get(()->dao().findByName(name));
     
 }
     public Image getImagesWithPath(String path){
         return get(()->dao().findByPath(path));
     }

      public List<Image> getImagesWithDate(Date date){
          return get(()->dao().findByDate(date));
      }
      
  @Override
       public List<Image> search(String search){
           return get(()->dao().findContaining(search));
       }
    
      @Override
    public Image createEntity(Map<String, String[]> parameterMap) {
    Image image =new Image();
   
  Date date ; 
Feed feed;
        
     if(parameterMap.containsKey(NAME)){
        image.setName(parameterMap.get(NAME)[0]);
    }
     if(parameterMap.containsKey(DATE)){
       image.setDate(new Date(parameterMap.get(DATE)[0]));
        
    }
     if(parameterMap.containsKey(PATH)){
        image.setPath(parameterMap.get(PATH)[0]);
    }
     if(parameterMap.containsKey(FEEDID)){
        image.setFeedid(new Feed(Integer.parseInt(parameterMap.get(FEEDID)[0])));
        
    } 
    
    
    return image;
    }
   
    
    @Override

    public List<String> getColumnNames() {
    return Arrays.asList("ID","NAME","DATE","PATH","FEEDID");
    
    }
//</editor-fold>

    @Override
    public List<String> getColumnCodes() {
 return Arrays.asList(ID,NAME,DATE,PATH,FEEDID);
    }
    @Override
    public List<?> extractDataAsList(Image e) {
    
        return Arrays.asList(e.getId(),e.getName(),e.getDate(),e.getPath(),e.getFeedid());
    
    }

  





    
}
