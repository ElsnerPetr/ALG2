/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UTILS;

import APP.Attempt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author CryHeroCZ
 */
public class Compare {
    
    /**
     * Metoda porovnávající příjmení.
     */
    public static Comparator<Attempt> LastNameComparator = (Attempt a1, Attempt a2) -> {
        String StudentName1 = a1.getLastName().toUpperCase();
        String StudentName2 = a2.getLastName().toUpperCase();
        
        return StudentName1.compareTo(StudentName2);
    };

    /**
     * Metoda porovnávající známky.
     */
    public static Comparator<Attempt> RankComparator = (Attempt a1, Attempt a2) -> {
        int rank1 = a1.getRank();
        int rank2 = a2.getRank();
        
        return rank1 - rank2;
    };

    
    /**
     * Metoda řadící pokusy podle známek.
     * @param attempts
     */
    public void sortByRank(ArrayList<Attempt> attempts) {
        Collections.sort(attempts, RankComparator);
    }

    /**
     * Metoda řadící pokusy podle jmen.
     * @param attempts
     */
    public void sortByName(ArrayList<Attempt> attempts) {
        Collections.sort(attempts,LastNameComparator);
    }

}
