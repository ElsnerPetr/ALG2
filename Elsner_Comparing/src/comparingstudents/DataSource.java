package comparingstudents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author CryHeroCZ
 */
public class DataSource {

    private static Student[] data = {
        new Student("Alice", "Mala", 345),
        new Student("Bob", "Velky", 123),
        new Student("Cyril", "Stredny", 567)
    };

    public static Student[] loadDataArray() {
        return Arrays.copyOf(data, data.length);
    }
    
    public static List<Student> loadDataAsList(){
        return Arrays.asList(data);
        //ArrayList<Student> students = new ArrayList<>(); //pro vytvoření  stímhle, musíme vytvořit prázdný a pak to tam přidat
        //students.addAll(Arrays.asList(data));
    }
    

}
