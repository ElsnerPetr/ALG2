package UTILS;

import APP.Attempt;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author Petr Elsner
 */
public class TextWriter extends Writer {

    /**
     * Metoda pro zápis dat do textového souboru .txt.
     *
     * @param resultFilepath cesta pro uložení souboru
     * @param attempts pole s pokusy.
     * @throws java.io.IOException
     */
    @Override
    public void saveResults(String resultFilepath, List<Attempt> attempts) throws IOException {
        File resultFile = new File(dataDirectory, resultFilepath);

        OutputStream os = new FileOutputStream(resultFile, true);

        try (PrintWriter pw = new PrintWriter(new OutputStreamWriter(os, "UTF-8"))) {

            for (Attempt attempt : attempts) {

                pw.println(attempt);

            }
        }
    }
}
