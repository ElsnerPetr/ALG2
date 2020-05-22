package cmd;

import java.io.File;

/**
 *
 * @author CryHeroCZ
 */
public class Roots extends Command {

    @Override
    public Location execute(File actualDir) {
        return new Location(actualDir, roots(actualDir, 0));
    }

    private String roots(File parent, int layer) {
        String roots = "";
        File[] files = parent.listFiles();
        for (File file : files) {
            for (int i = 0; i < layer; i++) {
                roots += " ";
            }
            roots += ("-" + file.getName() + "\n");
            if (file.isDirectory()) {
                roots += roots(file, layer + 1);
            }
        }
        return roots;
    }
}
