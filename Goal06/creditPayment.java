import java.util.Scanner;

/**
 * Jukebox class to model credit payment
 * @author Jason Jiang
 * @version 1.0
 */

public class creditPayment implements paymentLauncher{
    int money = 0;

    /**
     * Holds and  updates credit balance
     */
    public creditPayment(){}

    /**
     * Prompts user to add ccredit card balance
     * @return new balance  in cents.
     */
    @Override
    public int takePayment(){
        Scanner in = new Scanner(System.in);
        
        System.out.print("How much (in cents to add to machine) :>");
        int amountInCents = in.nextInt();

        money += amountInCents;
        
        // Balance
        // System.out.printf("Your balance is $%.2f%n", money);
        in.close();
        return amountInCents;
        
    }

    /**
     * Replicates a credit card swipe
     * @param amountInCents amount of cents to add to balance
     * @return new balance after credit card charge
     */
    public int takePayment(int amountInCents){
        money += amountInCents;
        return amountInCents;
        
    }
    /**
     * Replicates a charge to the balance of the card
     * @param cost_in_cents card charge to the credit card balance
    * @return updated credit user balance
     */
    public int subtractCertainBalance(int cost_in_cents){
        if (money >= cost_in_cents){
            money -= cost_in_cents;
            return cost_in_cents;
        }
        return money;
    }
    
    /**
     * Creates string that shows the refund that you have  back to your card
     * Also sets balance to 0
     * @return String
     */
    @Override
    public String returnFunds(){
        String out = "Your credit balance is " + money + " cents";
        money = 0 ;
       return out;
    }
}
