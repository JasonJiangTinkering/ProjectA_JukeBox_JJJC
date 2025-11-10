
/**
 * JukeBox Coin Box
 * Simple coin box for Jukebox
 * @author Jason Jiang, Joel Cipher
 * @version 1.0
 */

 // inital copied from : Vending Machine project
public class JukeBoxCoinBox {

    final private char acceptable_coin_chars[]={'p', 'n', 'd', 'q', 'h', 'g'};
    final private int acceptable_coin_vals[] = {1, 5, 10, 25, 50, 100};
    final private int changeSet[] = {0,0,0,0,0,0};
    public int changeValue(){
        int acc = 0;
        for (int i = 0; i < changeSet.length; i++){
            acc += acceptable_coin_vals[i] * changeSet[i];
        }
        return acc;
    }

    public JukeBoxCoinBox(){
        for (int i = 0; i < changeSet.length; i++){{
            changeSet[i] = 0;
        }}
    }

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

    public int subtractCertainBalance(int cost_in_cents){
        for (int i = 0; i < changeSet.length; i++){
            int spentAmount = cost_in_cents / acceptable_coin_vals[i];
            cost_in_cents -= spentAmount * acceptable_coin_vals[i];
            changeSet[i] -= spentAmount;
        }
        return cost_in_cents;
    }
    public String dispenseChange(){

        String outputStrings = "";
        int money = changeValue();
        outputStrings += "Golden Dollars: " + Math.min(money / 100, changeSet[5]) + ", ";
        money = money - (Math.min(money / 100, changeSet[5]) * 100);
        changeSet[5] -= Math.min(money / 100, changeSet[5]);
        outputStrings += "Half Dollars: " + Math.min(money / 50, changeSet[4]) + ", ";
        money = money - (Math.min(money / 50, changeSet[4]) * 50);
        changeSet[4] -= Math.min(money / 100, changeSet[4]);
        outputStrings += "Quarter: " +  Math.min(money / 25, changeSet[3]) + ", ";
        money = money - (Math.min(money / 25, changeSet[3]) * 25);
        changeSet[3] -= Math.min(money / 100, changeSet[3]);
        outputStrings += "Dime: " +  Math.min(money / 10, changeSet[2]) + ", ";
        money = money - (Math.min(money / 10, changeSet[2]) * 10);
        changeSet[2] -= Math.min(money / 100, changeSet[2]);
        outputStrings += "Nickel: " +  Math.min(money / 5, changeSet[1]) + ", ";
        money = money - (Math.min(money / 5, changeSet[1]) * 5);
        changeSet[1] -= Math.min(money / 100, changeSet[1]);        
        outputStrings += "Penny: " + Math.min(money, changeSet[0]) + ", ";
        money = money - (Math.min(money / 1, changeSet[0]));
        changeSet[0] -= Math.min(money / 100, changeSet[0]);
        if (money >0){
            System.out.println("Could not dispense enough coins, IOU: " + money + " cents");
        }
        return outputStrings;
    }
}
