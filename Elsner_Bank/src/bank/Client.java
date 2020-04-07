/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import java.util.ArrayList;

/**
 *
 * @author CryHeroCZ
 */
public abstract class Client {
    
   private String jmeno;
   private ArrayList<Account> accounts = new ArrayList();
   
   public Client(String jmeno){
       this.jmeno = jmeno;
   }
   
   public void plusUcet(double castka){
       this.accounts.add(new Account(castka));
   }
   
   public void plusUcet(){
       this.accounts.add(new Account());
   }
   
   public double celkemPenez(){
       double castka = 0;
       for (Account account : accounts) {  
             castka = castka + account.getPenize(); 
        }
       return castka;
   }
   
   
public abstract String Jmeno();
    
}
