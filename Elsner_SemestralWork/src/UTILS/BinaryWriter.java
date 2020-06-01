package UTILS;

import APP.Attempt;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(resultFile, true))) {

            for (Attempt attempt : attempts) {

                dos.writeUTF(attempt.getFirstName());
                dos.writeUTF(attempt.getLastName());
                dos.writeInt(attempt.getPoints());
                dos.writeInt(attempt.getRank());
                
                LocalDate localDate = attempt.getBirthdate();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String formattedString = localDate.format(formatter);
                System.out.println(formattedString);
                
                dos.writeUTF(formattedString);
            }
        }
    }

}
