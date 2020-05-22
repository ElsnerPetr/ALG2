package comparingstudents;

import java.util.Comparator;

/**
 *
 * @author CryHeroCZ
 */
public class CompareByAverage implements Comparator<Student>{
    @Override
    public int compare(Student o1, Student o2) {
        if (o1.calculateAverage() > o2.calculateAverage()) {
            return 1;
        } else if (o1.calculateAverage() == o2.calculateAverage()) {
            return 0;
        } else {
            return -1;
        }
    }
}
