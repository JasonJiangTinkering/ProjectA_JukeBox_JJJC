
/**
 * JukeBox Coin Box
 * Simple coin box for Jukebox
 * @author Jason Jiang
 * @version 1.0
 */

 // inital copied from : Vending Machine project
public class JukeBoxCoinBox {

    final private char acceptable_coin_chars[]={'p', 'n', 'd', 'q', 'h', 'g'};
    final private int acceptable_coin_vals[] = {1, 5, 10, 25, 50, 100};
    private int changeSet[] = {0,0,0,0,0,0};
    /**
     * Returns updated balance from coins that coinBox has as part of the user's balance
     * @return updated users's coin balance
     */
    public int changeValue(){
        int acc = 0;
        for (int i = 0; i < changeSet.length; i++){
            acc += acceptable_coin_vals[i] * changeSet[i];
        }
        return acc;
    }
    /**
     * Holds info about the amount of coins the current user has as part of balance.
     */
    public JukeBoxCoinBox(){
        for (int i = 0; i < changeSet.length; i++){{
            changeSet[i] = 0;
        }}
    }
    /**
     * Accepts coin and returns a changed balance
     * able to accept: 'p', 'n', 'd', 'q', 'h', 'g'
     * @param coinType the  coin  thats  being added
     * @return the new balance after adding coin
     */
    public int acceptCoin(char coinType){
        for (int i =0; i < acceptable_coin_chars.length; i++){
            if  (acceptable_coin_chars[i] == coinType){
                changeSet[i] ++;
                return acceptable_coin_vals[i];
            }
        }
        System.out.println("Please enter a valid coin.");
        return 0;
    }
    /**
     * subtractCertainBalance takes away correct amount of coins from the coin box
     * @param cost_in_cents how much to take away from user balance
     * @return modified balance
     */
    public int subtractCertainBalance(int cost_in_cents){
        int startingValue = cost_in_cents;
        for (int i = 0; i < changeSet.length; i++){
            int spentAmount = cost_in_cents / acceptable_coin_vals[i];
            cost_in_cents -= spentAmount * acceptable_coin_vals[i];
            changeSet[i] -= spentAmount;
        }
        return startingValue-cost_in_cents;
    }

    /**
     * Find out how many coins the machine should dispense
     * @return string with the amount of coins that are being dispensed to make up for the balance that is left over. 
     */
    public String dispenseChange(){

        String outputStrings = "";
        int money = changeValue();
        System.out.println(money);
        outputStrings += "Golden Dollars: " + money / 100 + ", ";
        money = money - (money / 100) * 100;
        outputStrings += "Half Dollars: " + money / 50 + ", ";
        money = money - (money / 50)*50;
        outputStrings += "Quarter: " + money / 25 + ", ";
        money = money - (money / 25)*25;
        outputStrings += "Dime: " +  money / 10 + ", ";
        money = money - (money / 10) * 10;
        outputStrings += "Nickel: " + money / 5 + ", ";
        money = money - (money / 5) * 5;
        outputStrings += "Penny: " + money + ", ";
        money = money - (money / 1);
        if (money >0){
            System.out.println("Could not dispense enough coins, IOU: " + money + " cents");
        }
        money = 0;
        changeSet = new int[6];
        return outputStrings;
    }
}
