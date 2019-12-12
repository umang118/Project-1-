/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import dao.FeedDAO;
import entity.Feed;
import entity.Host;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Umang
 */
public class FeedLogic extends GenericLogic<Feed,FeedDAO>{
    
  public static final String ID = "id";
  public static final String PATH = "path";
  public static final String TYPE = "type";
  public static final String NAME = "name";
  public static final String HOSTID = "hostid";

    public FeedLogic() {
        super(new FeedDAO());
    }

    

     @Override
    public List<Feed> getAll() {
    return get(()->dao().findAll());}

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Feed getWithId(int id) {
    return get(()->dao().findById(id));}
    
    
    public List<Feed> getFeedsWithHostID(int hostid){
        return get(()->{
            return dao().findByHostid(hostid);
        });
    }
    

     public Feed getFeedWithPath(String path){
         return get(()->dao().findByPath(path));
     }
      public List<Feed> getFeedWithType(String type){
     return get(()->dao().findByType(type));
     
 }

      public List<Feed> getFeedWithName(String name){
          return get(()->dao().findByName(name));
      }
      
  @Override
       public List<Feed> search(String search){
           return get(()->dao().findContaining(search));
       }
  
    
  
    @Override
    public Feed createEntity(Map<String, String[]> parameterMap) {
        
        Feed feed = new Feed();
        Host host;
    
 
  if(parameterMap.containsKey(NAME)){
        feed.setName(parameterMap.get(NAME)[0]);
  }
    
    if(parameterMap.containsKey(TYPE)){
       feed.setType(parameterMap.get(TYPE)[0]);
    }     
     if(parameterMap.containsKey(PATH)){
        feed.setPath(parameterMap.get(PATH)[0]);
     }
    if(parameterMap.containsKey(HOSTID)){
        feed.setHostid(new Host(Integer.parseInt(parameterMap.get(HOSTID)[0])));
        
    } 
    
    
    return feed ;
    }

    @Override
    public List<String> getColumnNames() {
        return Arrays.asList("ID","NAME","TYPE","PATH","HOSTID");
    }

    @Override
    public List<String> getColumnCodes() {
    return Arrays.asList(ID,NAME,TYPE,PATH,HOSTID);
    }

    @Override
    public List<?> extractDataAsList(Feed e) {
        return Arrays.asList(e.getId(),e.getName(),e.getType(),e.getPath(),e.getHostid());
    }

   

    
}
