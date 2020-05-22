package cmd;

/**
 *
 * @author CryHeroCZ
 */
public interface CmdInterface {
    public boolean isRunning();
    
    public String getActualDir();
    
    public String parseAndExecute(String line);
}
