package comparingstudents;

import MyComparing.CompareInterface;
import java.util.Objects;

/**
 *
 * @author CryHeroCZ
 */
public class Student implements CompareInterface, Comparable<Student> {

    //data
    private String firstName;
    private String lastName;
    private int studentNumber;
    private int[] grades;

    public Student(String firstName, String lastName, int studentNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentNumber = studentNumber;
    }

    public void setGrades(int[] grades) {
        this.grades = grades;
    }

    public double calculateAverage() {
        int sum = 0;
        double average;
        for (int i = 0; i < grades.length; i++) {
            sum += grades[i];
        }
        average = sum/(grades.length);
        return average;
    }

    @Override
    public String toString() {
        return String.format("%10s%10s%10d", firstName, lastName, studentNumber, calculateAverage());
    }

    public boolean isBigger(Student student) {
        return this.studentNumber > student.studentNumber;
    }

    public boolean isBigger(CompareInterface o) {
        return this.studentNumber > ((Student) o).studentNumber;
    }

//    @Override
//    public int compareTo(Object o) {
//        return this.studentNumber - ((Student)o).studentNumber;
//    }
    @Override
    public int compareTo(Student o) {
        return this.studentNumber - o.studentNumber;
    }
//insert - přepsání equals and hash
    //pri zmene equals zmenit i hash

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.firstName);
        hash = 37 * hash + Objects.hashCode(this.lastName);
        hash = 37 * hash + this.studentNumber;
        return hash;
    }
//default in Object

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public int[] getGrades() {
        return grades;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        if (this.studentNumber != other.studentNumber) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean bigger(Object o1, Object o2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
