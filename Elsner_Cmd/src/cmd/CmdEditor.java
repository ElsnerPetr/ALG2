package cmd;

import java.io.File;

/**
 *
 * @author CryHeroCZ
 */
public class CmdEditor implements CmdInterface {

    private boolean isRunning;
    private File actualDir;
    private Command command;

    //konstruktor
    public CmdEditor() {
        isRunning = true;
        actualDir = new File(System.getProperty("user.dir"));

    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public String getActualDir() {
        return actualDir.getAbsolutePath();
    }

    @Override
    public String parseAndExecute(String line) {
        command = Parser.parse(line);
        if(command==null){
            return "";
        }
        Location location = command.execute(actualDir);
        actualDir = location.getActualDir();
        if (actualDir == null) {
            isRunning = false;
        }
        return location.getMessage();
    }
    
    //z hodiny:
//    @Override
//    public String parseAndExecute(String line) {  //parsování = rozsekání věty na části
//    //parse
//    command =  Parser.parse(line);
//    //execute
//    isRunning = false; //testování
//    return command.execute(actualDir);
//    }

}
