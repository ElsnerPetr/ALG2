package competition.app;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 *
 * @author CryHeroCZ
 */
public class Runner implements Comparable<Runner> {

    private int number;
    private String firstname;
    private String lastname;
    private LocalTime startTime;
    private LocalTime finishTime;
    public static DateTimeFormatter dtfstart = DateTimeFormatter.ofPattern("HH:mm:ss");  //formater -- v jakém formátě se bude čas vyskytovat (static protože bude stejný pro všecky běžce)
    public static DateTimeFormatter dtffinish = DateTimeFormatter.ofPattern("HH:mm:ss:SSS");

    public Runner(int number, String firstname, String lastname) {
        this.number = number;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public void setStartTime(String startTime) {
        this.startTime = LocalTime.parse(startTime, dtfstart); //jaký text a podle jakého formátu to má udělat
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = LocalTime.parse(finishTime, dtffinish);
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getFinishTime() {
        return finishTime;
    }

    public String getStartTimeString() { //aby nám to udělalo pořádný formát na čas a nevynechávalo to poslední nuly
        return startTime.format(dtfstart);
    }

    public String getFinishTimeString() {
        return finishTime.format(dtffinish);
    }

    public int getNumber() {
        return number;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public LocalTime runningTime() {
        return LocalTime.ofNanoOfDay(finishTime.toNanoOfDay() - startTime.toNanoOfDay());
    }

    @Override
    public String toString() {
        return String.format("%-4d%-10s%-10s%-15s%-15s%-15s", number, firstname, lastname, getStartTimeString(), //tohle asi lepší
                getFinishTimeString(), runningTime().format(dtffinish));
//        return "Runner{" + "number=" + number  
//                + ", firstname=" + firstname
//                + ", lastname=" + lastname
//                + ", startTime=" + getStartTimeString()
//                + ", finishTime=" + getStartTimeString()
//                + '}';
    }

    @Override
    public int compareTo(Runner o) {
        return this.runningTime().compareTo(o.runningTime());
    }

    public static void main(String[] args) {
        Runner r = new Runner(101, "Alice", "Mala");
        r.setStartTime("09:00:00");
        r.setFinishTime("09:30:01:000");
        System.out.println(r);
    }

}
