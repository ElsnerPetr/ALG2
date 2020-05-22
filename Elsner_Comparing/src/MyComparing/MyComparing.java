package MyComparing;

import MyComparing.CompareInterface;
import comparingstudents.DataSource;
import comparingstudents.Student;
import java.util.List;

public class MyComparing {

    public static void main(String[] args) {
        Student[] students = DataSource.loadDataArray();

        print(students);
        System.out.println("");
        System.out.println("Sort by number:");
        //sortByNumber(students);
        sort(students);
        print(students);
    }

    public static void print(Object[] array) {
        for (Object object : array) {
            System.out.println(object);
        }
    }

    public static void print(List list) {
        for (Object object : list) {
            System.out.println(object);
        }
    }

    public static void sortByNumber(Student[] array) {//bubble sort
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 1; j < array.length - i; j++) {
                if (array[j - 1].isBigger(array[j])) {
                    Student temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
        }
    }

    public static void sort(CompareInterface[] array) { //bubble sort
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 1; j < array.length - i; j++) {
                if (array[j - 1].isBigger(array[j])) {
                    CompareInterface temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
        }
    }

    public static void sort(Object[] array, CompareInterface o) {//třídí pole jakychkoli objektů
        for (int i = 0; i < array.length - 1; i++) {             //metoda bigger objektu typově kompatibilniho s compareinterface definuje jak třídit
            for (int j = 1; j < array.length - i; j++) {
                if (o.bigger(array[j - 1], array[j])) {
                    Object pom = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = pom;
                }

            }

        }
    }
}
