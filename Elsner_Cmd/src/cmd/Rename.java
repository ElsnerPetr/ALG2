package cmd;

import java.io.File;

/**
 *
 * @author CryHeroCZ
 */
public class Rename extends Command {
    @Override
    public Location execute(File actualDir) {
        if (params.length == 3) {
            File f1 = new File(actualDir.getAbsolutePath()+"\\"+params[1]);
            File f2 = new File(actualDir.getAbsolutePath()+"\\"+params[2]);
            f1.renameTo(f2);
            return new Location(actualDir,"Soubor přejmenován\n");
        } else {
            return new Location(actualDir, "Nevalidní příkaz\n");
        }
    }
}
