package elsner_hurricane;

/**
 *
 * @author CryHeroCZ
 */
public class Hurricane implements Comparable<Hurricane>{
    int year;
    String month;
    int pressure;
    int speed;
    String name;

    public Hurricane(int year, String month, int pressure, int speed, String name) {
        this.year = year;
        this.month = month;
        this.pressure = pressure;
        this.speed = speed;
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public String getName() {
        return name;
    }
    
    @Override
    public int compareTo(Hurricane h){
        if(this.speed > h.speed){
            return 1;
        }else if(this.speed == h.speed){
            return 0;
        }
        return -1;
    }

    @Override
    public String toString() {
        return  String.format("%15s %15d %15s %15d %15d", name,year,month,pressure,speed);
    }
    
    public double speedInKmh(){
        double speedKmh = speed * 1.852;
        return speedKmh;
    }
    
    public String cathegory(){
        double speed = speedInKmh();
        if (speed < 154){
            return "Category 1";
        }else if(speed < 178){
            return "Category 2";
        }else if(speed < 209){
            return "Category 3";
        }else if(speed < 252){
            return "Category 4";
        }else{
            return "Category 5";
        }
                
    }
    
    
}