package comparingstudents;

import MyComparing.CompareInterface;
/**
 *
 * @author CryHeroCZ
 */
public class ComparatorByGrade implements CompareInterface{
    @Override
    public boolean bigger(Object o1, Object o2) {
        return ((Student)o1).calculateAverage()>((Student)o2).calculateAverage();
    }

    @Override
    public boolean isBigger(CompareInterface o) {
        System.out.println("ehm");
        return false;
    }

    
}
