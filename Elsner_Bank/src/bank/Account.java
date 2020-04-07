package bank;

/**
 *
 * @author CryHeroCZ
 */
public class Account {
//data

    private double penize = 0;

    public Account() {
    }

    public Account(double penize) {
        this.penize = penize;
    }
    
    public void vklad(double vklad){
        this.penize = this.penize + vklad;
    }
    public void vyber(double vyber){
        if ((penize - vyber)<0){
            throw new IllegalArgumentException("Tento účet neposkytuje vybírat peněžní prostředky do dluhu.");
        }
        this.penize = this.penize - vyber;
        
    }

    public double getPenize() {
        return penize;
    }
    

}
