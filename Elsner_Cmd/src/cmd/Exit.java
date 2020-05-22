package cmd;

import java.io.File;

/**
 *
 * @author CryHeroCZ
 */
public class Exit extends Command{
    
    @Override
    public Location execute(File actualDir) {
        return new Location(null,"EXIT\n");
    }
}
