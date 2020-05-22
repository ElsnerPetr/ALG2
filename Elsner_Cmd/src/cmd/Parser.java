package cmd;

/**
 *
 * @author CryHeroCZ
 */
public class Parser {

    public static Command parse(String line) {
        String[] p = line.split(" +"); //+ je že tam může být jedna a víc mezer -- vytvořili jsme pole
        //p[0] dir; p[1] -e; p[2] .java
        //TODO prazdny prikaz ---------------------------------
        char first = Character.toUpperCase(p[0].charAt(0)); //chceme char na nule 
        String name = Command.COMMAND_PACKAGE + "." + first + p[0].substring(1);  // vytvořili jsme název třídy cmd.Dir
        try {//předělání z povinné vyjámky na nepovinnou
            Class c = Class.forName(name);
            Command command = (Command) c.newInstance();
            command.setParams(p);
            return command;
        } catch (Exception e){
            throw new RuntimeException("Nepodařilo se příkaz naparsovat!");
        } 
            
        }
    }
