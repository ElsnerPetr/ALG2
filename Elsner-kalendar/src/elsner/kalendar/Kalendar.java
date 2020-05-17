package elsner.kalendar;

import java.util.Arrays;

/**
 *
 * @author CryHeroCZ
 */
public class Kalendar {

    /**
     * @param args the command line arguments
     */
    //data
    private int den;
    private int mesic;
    private int rok;

    //konstruktor
    public Kalendar(int den, int mesic, int rok) {
        this.den = den;
        this.mesic = mesic;
        this.rok = rok;
    }

    public int getDen() {
        return den;
    }

    public int getMesic() {
        return mesic;
    }

    public int getRok() {
        return rok;
    }

    public int getDenvTydnu() {
        int d = this.den;
        int r = this.rok;
        int m = this.mesic;
        if (m == 1) {
            m = 13;
            r--;
        }
        if (m == 2) {
            m = 14;
            r--;
        }
        int k = r % 100;
        int j = r / 100;
        int h = (d + 13 * (m + 1) / 5 + k + k / 4 + j / 4 - 2 * j) % 7;
        int vd = ((h + 5) % 7) + 1;
        return vd;
    }

    public int dnyvMesici() {
        int[] pocet = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return pocet[getMesic() - 1];
    }

    public void nextMesic() {
        this.den = 1;
        if (this.mesic == 12) {
            this.rok++;
            this.mesic = 1;
        } else {
            this.mesic++;
        }
    }

    public void prevMesic() {
        this.den = 1;
        if (this.mesic == 1) {
            this.mesic = 12;
            this.rok--;
        } else {
            this.mesic--;
        }
    }

    public String getKalendar() {
        StringBuilder kalendar = new StringBuilder();
        kalendar.append(String.format("%2d %4d%n", mesic, rok));
        String[] week = new String[]{"PO", "UT", "ST", "CT", "PA", "SO", "NE"};
        kalendar.append(Arrays.toString(week));
        int[] days = new int[7];
        int ind = getDenvTydnu();
        int citac1 = 1;
        int citac2 = 31;
        int dnyvMesici = dnyvMesici();
        
        if (getMesic() == 3) {
            citac2 = 29;
        } else if (dnyvMesici == 31 && getMesic() != 1 && getMesic() != 8) {
            citac2 = 30;
        }
        for (int i = ind; i < days.length; i++) {
            days[i] = citac1++;
        }
        for (int i = ind - 1; i >= 0; i--) {
            days[i] = citac2;
            citac2--;
        }
        
        kalendar.append("\n");
        kalendar.append(Arrays.toString(days));
        for (int i = 0; i < 5; i++) {
            kalendar.append("\n");
            for (int j = 0; j < days.length; j++) {
                if (citac1 <= dnyvMesici()) {
                    days[j] = citac1;
                    citac1++;
                } else {
                    citac1 = 1;
                    days[j] = citac1++;
                }
            }
            kalendar.append(Arrays.toString(days));
        }
        return kalendar.toString();
    }

    public static void main(String[] args) {
        Kalendar k = new Kalendar(7, 8, 2020);
        System.out.println(k.getDenvTydnu());
        System.out.println(k.getKalendar());

    }

}
