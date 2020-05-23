package competition.app;

import competition.filehandling.Writer;
import competition.filehandling.TextWriter;
import competition.filehandling.BinaryWriter;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.activation.UnsupportedDataTypeException;

/**
 *
 * @author CryHeroCZ
 */
public class Competition {

    private ArrayList<Runner> runners = new ArrayList<>();

    public void load(String startFilepath, String finishFilepath) throws FileNotFoundException, IOException {  //throws přehodí chybu nenalezení file už jako chybu metody a ne jen tady uvnitř
        if (startFilepath.endsWith(".txt")) {
            loadStartText(startFilepath);
        } else if (startFilepath.endsWith(".dat")) {
            loadStartBinary(startFilepath);
        } else {
            throw new UnsupportedDataTypeException("start type not supported");
        }
        if (finishFilepath.endsWith(".txt")) {
            loadFinishText(finishFilepath);
        } else if (finishFilepath.endsWith(".dat")) {
            loadFinishBinary(finishFilepath);
        } else {
            throw new UnsupportedDataTypeException("finish type not supported");
        }
    }

    private Runner findRunner(int number) {
        for (Runner runner : runners) {
            if (runner.getNumber() == number) {
                return runner;
            }
        }
        throw new NoSuchElementException("Běžec s číslem " + number + " nebyl na startu."); //snažíme se vyjímky nepoužívat na nromální chod programu, ale sem by se to nemělo dostat
    }

    public String getResults() { //vypíšení runners
        Collections.sort(runners);
        StringBuilder sb = new StringBuilder("");
        int n = 1;
        for (Runner runner : runners) {
            sb.append(String.format("%-2d. %s%n", n, runner));
            n++;
        }
        return sb.toString();
    }
//zapis do souboru

    public void saveResults(String resultFilepath) throws IOException {

        Writer w = null;
        if (resultFilepath.endsWith(".txt")) {
            w = new TextWriter();
            System.out.println("text");  //kontrola

        } else if (resultFilepath.endsWith(".dat")) {
            w = new BinaryWriter();
        } else {
            throw new IllegalArgumentException("nepodporovaná přípona souboru");
        }
        w.saveResults(resultFilepath, runners);
    }
    
    private void loadStartText(String startFilepath) throws FileNotFoundException, IOException {
        File startFile = new File(Writer.dataDirectory, startFilepath);
        try (Scanner inStart = new Scanner(startFile)) {
            while (inStart.hasNext()) {
                int number = inStart.nextInt();
                String firstName = inStart.next();
                String lastName = inStart.next();
                String startTime = inStart.next();
                try {
                    Runner r = new Runner(number, firstName, lastName);
                    r.setStartTime(startTime);
                    runners.add(r);
                } catch (NoSuchElementException e) {
                    System.err.println("runner not found");
                }
            }
        }
    }

    private void loadFinishText(String finishFilepath) throws FileNotFoundException, IOException {
        File finishFile = new File(Writer.dataDirectory, finishFilepath);
        try (BufferedReader inFinish = new BufferedReader(new FileReader(finishFile))) { //automatické uzavření souboru
            String line;
            while ((line = inFinish.readLine()) != null) {
                String[] parts = line.split("[ ]+");
                Runner r = findRunner(Integer.parseInt(parts[0]));
                r.setFinishTime(parts[1]);
            }
        }
    }

    private void loadStartBinary(String startFilepath) throws IOException {
        BinaryWriter bw = new BinaryWriter();
        startFilepath = Character.toUpperCase(startFilepath.charAt(0)) + startFilepath.substring(1);
        bw.createStart(startFilepath);
        File startFile = new File(Writer.dataDirectory, startFilepath);
        try (DataInputStream dis = new DataInputStream((new FileInputStream(startFile)))) {
            boolean isEnd = false;
            while (!isEnd) {
                try {
                    int number = dis.readInt();
                    String firstName = dis.readUTF();
                    String lastName = dis.readUTF();
                    long startTimeNano = dis.readLong();
                    String startTime = (LocalTime.ofNanoOfDay(startTimeNano)).format(Runner.dtfstart);
                    try {
                        Runner r = new Runner(number, firstName, lastName);
                        r.setStartTime(startTime);
                        runners.add(r);
                    } catch (NoSuchElementException e) {
                        System.err.println("runner not found");
                    }
                } catch (EOFException e) {
                    isEnd = true;
                }
            }
        }

    }

    private void loadFinishBinary(String finishFilepath) throws IOException {
        BinaryWriter bw = new BinaryWriter();
        finishFilepath = Character.toUpperCase(finishFilepath.charAt(0)) + finishFilepath.substring(1);
        bw.createFinish(finishFilepath);
        File finishFile = new File(Writer.dataDirectory, finishFilepath);
        try (DataInputStream dis = new DataInputStream((new FileInputStream(finishFile)))) {
            boolean isEnd = false;
            while (!isEnd) {
                try {
                    int number = dis.readInt();
                    long finishTimeNano = dis.readLong();
                    String finishTime = (LocalTime.ofNanoOfDay(finishTimeNano)).format(Runner.dtffinish);
                    Runner r = findRunner(number);
                    r.setFinishTime(finishTime);
                } catch (EOFException e) {
                    isEnd = true;
                }
            }
        }
    }

}
//domácí úkol co jsme udělali pto rápis tak udělat pro ... vstup? asi
