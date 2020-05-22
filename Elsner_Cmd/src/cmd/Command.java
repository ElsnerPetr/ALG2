package cmd;

import java.io.File;

/**
 *
 * @author CryHeroCZ
 */
public abstract class Command {   //abstraktem mohu asi přidávat i další commands
    public static final String COMMAND_PACKAGE = "cmd";
    protected String[] params; //dir | -e  | .java   (rozdělí příkaz na tři prvky)
    
    public void setParams(String[] params){  //setter
         this.params = new String[params.length];        
         System.arraycopy(params, 0, this.params, 0, params.length);
    }
    
    public abstract Location execute(File actualDir);
//        switch (params[0]){  //kdyby tam byl předem určený počet příkazů
//            case "dir": 
//                dir();
//                break;
//            case "cd": 
//                cd();
//                break;           
//       }
    
    
}
