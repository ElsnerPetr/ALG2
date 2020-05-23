package competition.filehandling;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import competition.app.Runner;
import static competition.filehandling.Writer.dataDirectory;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.Scanner;

/**
 *
 * @author CryHeroCZ
 */
public class BinaryWriter extends Writer{

    @Override
    public void saveResults(String resultFilepath, List<Runner> runners) throws IOException {
        File resultFile = new File(dataDirectory, resultFilepath);
        
        try(DataOutputStream dos = new DataOutputStream(new FileOutputStream(resultFilepath))){
            dos.writeUTF("Nové výsledky");
            int n = 1;
            for (Runner runner : runners) {
                dos.writeInt(n);
                dos.writeChar('.');
                dos.writeUTF(runner.getFirstname()); //jedna možnost
                
                int nChars = runner.getLastname().length();
                dos.writeInt(nChars);//další možnost na uložení, nejpve uložím počet znaků a pak to postupně ukládám
                for (int i = 0; i < nChars; i++) {
                    dos.writeChar(runner.getLastname().charAt(i));                    
                }
                
                dos.writeLong(runner.runningTime().toNanoOfDay());
                n++;
            }            
        }
    } 
    
    
    public void createStart(String startFilePath) throws FileNotFoundException, IOException {
        File binaryFile = new File(dataDirectory,startFilePath);
        File textFile = new File(dataDirectory,startFilePath.replace(".dat", ".txt"));
        try (Scanner out = new Scanner(textFile)) {
            try (DataOutputStream in = new DataOutputStream(new FileOutputStream(binaryFile))) {
                while (out.hasNext()) {
                    in.writeInt(out.nextInt());
                    in.writeUTF(out.next());
                    in.writeUTF(out.next());
                    String startTime = out.next();
                    LocalTime start = LocalTime.parse(startTime, Runner.dtfstart);
                    in.writeLong(start.toNanoOfDay());
                }
            }
        }

    }

    public void createFinish(String finishFilePath) throws FileNotFoundException, IOException {
        File binaryFile = new File(dataDirectory,finishFilePath);
        File textFile = new File(dataDirectory,finishFilePath.replace(".dat", ".txt"));
        try (Scanner out = new Scanner(textFile)) {
            try (DataOutputStream in = new DataOutputStream(new FileOutputStream(binaryFile))) {
                while (out.hasNext()) {
                    in.writeInt(out.nextInt());
                    String finishTime = out.next();
                    LocalTime finish = LocalTime.parse(finishTime, Runner.dtffinish);
                    in.writeLong(finish.toNanoOfDay());
                }
            }
        }
    }
}
