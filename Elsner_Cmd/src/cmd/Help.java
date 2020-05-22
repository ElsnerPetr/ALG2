package cmd;

import java.io.File;

/**
 *
 * @author CryHeroCZ
 */
public class Help extends Command{

    @Override
    public Location execute(File actualDir) {
        String help = "HELP\n" 
                + String.format("%-30s %s%n", "help", "Display help")
                + String.format("%-30s %s%n", "roots", "Display tree of current folder")
                + String.format("%-30s %s%n", "dir", "Display list of files and folders")
                + String.format("%-30s %s%n", "dir [-o]", "Display an ordered list of files and folders")
                + String.format("%-30s %s%n", "dir [-e] [file extension]", "Display a list of files and folders with a specified extension")
                + String.format("%-30s %s%n", "dir [-s] [size]", "Display a list of files and folders bigger than a specified size")
                + String.format("%-30s %s%n", "cd ..", "Change directory - move to the folder one level higher")
                + String.format("%-30s %s%n", "cd [folder name]", "Change directory - move to a specific folder")
                + String.format("%-30s %s%n", "mkdir [folder name]", "Create new folder")
                + String.format("%-30s %s%n", "rename [nameFrom] [nameTo]", "Rename folder or file")
                + String.format("%-30s %s%n", "exit", "End CMD");
        return new Location(actualDir, help);
    }
    
}
