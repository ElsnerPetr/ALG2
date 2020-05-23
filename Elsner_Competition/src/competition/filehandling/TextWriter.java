package competition.filehandling;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import competition.app.Runner;
import java.io.File;

/**
 *
 * @author CryHeroCZ
 */
public class TextWriter extends Writer {
//zapsání do textového souboru

    
    @Override
    public void saveResults(String resultFilepath, List<Runner> runners) throws IOException {
        File resultFile = new File(dataDirectory, resultFilepath);
        
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(resultFilepath, true)))){ //to true udělá, že při pojmenování výsledku stejně, jako už existuje, že to tam hodnoty přidá a nepřepíše to co tam už je
//do "finish" po použití try místo catch to skočí dycky (ale to jsme smazali)
         
            //PrintWriter pw = new PrintWriter(new OutputStreamWtriter(System.out, "Cp1250"), true); //Cp1250 = windows console; různé konstruktory ale welp
            pw.println("nové výsledky");
            int n = 1;
            for (Runner runner : runners) {
                pw.print(n + ". ");
                pw.println(runner);
                n++;
            }
        }
    }

}
