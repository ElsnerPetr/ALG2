package app;

import java.util.ArrayList;
import bank.Client;
import bank.Company;
import bank.Person;

/**
 *
 * @author CryHeroCZ
 */
public class Main {

    public static void main(String[] args) {
        ArrayList<Client> clients = new ArrayList();
        clients.add(new Person("Pekar"));
        clients.add(new Person("Svecova"));
        clients.add(new Company("Skoda"));

        clients.get(0).plusUcet(1000);
        clients.get(0).plusUcet(500);
        clients.get(1).plusUcet(1200);
        clients.get(2).plusUcet(120);

        System.out.println("Jmeno klienta: " + clients.get(0).Jmeno());
        System.out.println("Celkova castka: " + clients.get(0).celkemPenez());

        System.out.println("Jmeno klienta: " + clients.get(1).Jmeno());
        System.out.println("Celkova castka: " + clients.get(1).celkemPenez());

        System.out.println("Jmeno klienta: " + clients.get(2).Jmeno());
        System.out.println("Celkova castka: " + clients.get(2).celkemPenez());

    }

}
