package comparingstudents;


import java.util.Comparator;

/**
 *
 * @author CryHeroCZ
 */
public class CompareByFirstName implements Comparator<Student>{
    
    public CompareByFirstName() {
    }

    @Override
    public int compare(Student o1, Student o2) {
    return o1.getFirstName().compareTo(o2.getFirstName());
    }

}
