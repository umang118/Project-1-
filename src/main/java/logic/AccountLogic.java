/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import dao.AccountDAO;

import entity.Account;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
/**
 *
 * @author Umang
 */
public class AccountLogic extends GenericLogic<Account,AccountDAO>{

        
  public static final String DISPLAY_NAME = "firstName";
  public static final String PASSWORD = "joined";
  public static final String USER = "lastNme";
  public static final String ID = "id";

    
    public AccountLogic() {
        super(new AccountDAO());
    }

    
        @Override
    public List<Account> getAll() {
    return get(()->dao().findAll()); 
    }

    @Override
    public Account getWithId(int id) {
    return get(()->dao().findById(id));
    }
    
 
          public Account getAccountWithDisplayName(String displayName){
          return get(()->dao().findByDisplayName(displayName));
      }

     public Account getAccountWithUser(String user){
         return get(()->dao().findByUser(user));
     }
     
      public List<Account> getAccountWithPassword(String password){
     return get(()->dao().findByPassword(password));
     
 }
           public Account getAccountWith(String username,String password){
         return get(()->dao().validateUser(username, password));
     }



      
  @Override
       public List<Account> search(String search){
           return get(()->dao().findContaining(search));
       }
       
       
                  @Override
    public Account createEntity(Map<String, String[]> parameterMap) {
       
        Account account =new Account();
        
   
        if(parameterMap.containsKey(DISPLAY_NAME)){
        account.setDisplayName(parameterMap.get(DISPLAY_NAME)[0]);
    
    }
        if(parameterMap.containsKey(USER)){
       account.setUser(parameterMap.get(USER)[0]);
        
    }
        if(parameterMap.containsKey(PASSWORD)){
        account.setPassword(parameterMap.get(PASSWORD)[0]);
    
    }
    return account;
    }
    

    @Override
    public List<String> getColumnNames(){
 
        return Arrays.asList("ID","DISPLAY_NAME","USER","PASSWORD");
        
    }

    @Override
    public List<String> getColumnCodes() {
   return Arrays.asList(ID,DISPLAY_NAME,USER,PASSWORD); 
    }

    @Override
    public List<?> extractDataAsList(Account e) {
   
        return Arrays.asList(e.getId(),e.getDisplayName(),e.getUser(),e.getPassword());
    }

 


    
}
