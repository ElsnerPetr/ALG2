package UTILS;

import APP.Attempt;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Petr Elsner
 */
public abstract class Writer {

    public static File dataDirectory = new File(System.getProperty("user.dir") + File.separator + "data");//+ "data" + File.separator

    /**
     * Metoda pro uložení souborů.
     *
     * @param resultFilepath výsledná lokace pro uložení souboru
     * @param attempts pole pokusů pro uložení do souboru
     * @throws java.io.IOException
     */
    public abstract void saveResults(String resultFilepath, List<Attempt> attempts) throws IOException;
}
