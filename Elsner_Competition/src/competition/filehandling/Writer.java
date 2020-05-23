package competition.filehandling;

import competition.app.Runner;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author CryHeroCZ
 */
public abstract class Writer {
    public static File dataDirectory = new File(System.getProperty("user.dir")+ File.separator + "data"); //zeptáme se jak se jmenuje pracovní adresář a k tomu přidáme to co víme
    //file separator umožní pouštět i na jiných operačních systémech, protože každej je zas jinej protože jsou to všechno kreténi
    public abstract void saveResults(String resultFilepath, List<Runner> runners) throws IOException;
}
//aby se nám nepvalovali soubory všude, tak udělat složku "data"