package elsner_hurricane;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import static java.util.Collections.sort;
import java.util.Scanner;

/**
 *
 * @author CryHeroCZ
 */
public class HurricanesEditor{
    private ArrayList<Hurricane> hurricanes = new ArrayList<>();

    public void load() throws FileNotFoundException{
        File file = new File("hurricanedata.txt");
        Scanner inFile = new Scanner(file);
        while(inFile.hasNext()){
            int year = inFile.nextInt();
            String month = inFile.next();
            int pressure = inFile.nextInt();
            int speed = inFile.nextInt();
            String name = inFile.next();
            Hurricane h = new Hurricane(year,month,pressure,speed,name);
            hurricanes.add(h);
        }
    }

    public String getInfoFromTo(int yearFrom, int yearTo) {
        StringBuilder sb = new StringBuilder("");
        if(yearFrom != Integer.MIN_VALUE){
           sb.append(String.format("Huricany od roku %d do roku %d:\n",yearFrom, yearTo)); 
        }
        sb.append(String.format("%15s %15s %15s %15s %15s\n","name","year","month","pressure[hPa]","speed[kn]"));
        for (Hurricane hurricane : hurricanes) {
            if(hurricane.getYear() >= yearFrom && hurricane.getYear() <= yearTo){
                sb.append(hurricane);
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public String getCategoryAndSpeed(String name) {
        for (Hurricane hurricane : hurricanes) {
            if(hurricane.getName().equals(name)){
                return String.format("%10s: %15s, rychlost: %4.2f km/h",name,hurricane.cathegory(),hurricane.speedInKmh());
            }
        }
        return "Tento hurican nenni v zaznamech.";
    }

    public String getSortedBySpeed() {
        sort(hurricanes);
        return getInfoFromTo(Integer.MIN_VALUE,Integer.MAX_VALUE);
    }
    
}