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
public class Company extends Client{
    private String jmeno;

    public Company(String jmeno) {
        super(jmeno);
        this.jmeno = jmeno;
    }

    public String Jmeno() {
        return "firma " + jmeno;
    }
}
