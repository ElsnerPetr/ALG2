package cmd;

import java.io.File;

/**
 *
 * @author CryHeroCZ
 */
public class Mkdir extends Command{
    
    @Override
    public Location execute(File actualDir) {
        if (params.length == 2) {
            File file = new File(actualDir.getAbsolutePath() + "\\" + params[1]);
            file.mkdir();
            return new Location(actualDir, "Soubor vytvořen\n");

        } else {
            return new Location(actualDir, "Nevalidní příkaz\n");
        }
    }
}
