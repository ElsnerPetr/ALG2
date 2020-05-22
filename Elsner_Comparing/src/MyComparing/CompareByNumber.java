package MyComparing;

import comparingstudents.Student;

/**
 *
 * @author CryHeroCZ
 */
public class CompareByNumber implements CompareInterface{
    @Override
    public boolean isBigger(CompareInterface o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean bigger(Object o1, Object o2) {
      return  ((Student)o1).getStudentNumber() > ((Student)o2).getStudentNumber();
    }
}
