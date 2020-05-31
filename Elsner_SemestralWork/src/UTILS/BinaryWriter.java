package UTILS;

import APP.Attempt;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.io.File;

/**
 *
 * @author Petr Elsner
 */
public class BinaryWriter extends Writer {

    /**
     * Metoda pro zápis dat do souboru.dat.
     *
     * @param resultFilepath cesta pro uložení souboru
     * @param attempts pole s pokusy.
     * @throws java.io.IOException
     */
    @Override
    public void saveResults(String resultFilepath, List<Attempt> attempts) throws IOException {
        File resultFile = new File(dataDirectory, resultFilepath);

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(resultFilepath,true))) {

            for (Attempt attempt : attempts) {

                dos.writeUTF(attempt.getFirstName());

                int nChars = attempt.getLastName().length();
                dos.writeInt(nChars);
                for (int i = 0; i < nChars; i++) {
                    dos.writeChar(attempt.getLastName().charAt(i));
                }

                dos.writeChars(attempt.getBirthdate().toString());

            }
        }
    }

}
