/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

/**
 *
 * @author CryHeroCZ
 */
public class Person extends Client {

    private String jmeno;

    public Person(String jmeno) {
        super(jmeno);
        this.jmeno = jmeno;
    }

    @Override
    public String Jmeno() {
        if (jmeno.charAt(jmeno.length() - 3) == 'o' && jmeno.charAt(jmeno.length() - 2) == 'v' && jmeno.charAt(jmeno.length() - 1) == 'a') {
            return "pani " + jmeno;
        }
        return "pan " + jmeno;
    }

}
