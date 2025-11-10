package GoalSet2;
import java.util.Scanner;

/**
 * Jukebox class to model credit payment
 * @author Jason Jiang
 * @version 1.0
 */

public class creditPayment implements paymentLauncher{
    int money = 0;
    @Override
    public int takePayment(){
        Scanner in = new Scanner(System.in);
        
        System.out.print("How much (in cents to add to machine) :>");
        int amountInCents = in.nextInt();

        money += amountInCents;
        
        // Balance
        // System.out.printf("Your balance is $%.2f%n", money);
        return amountInCents;
        
    }

    public int subtractCertainBalance(int cost_in_cents){
        if (money >= cost_in_cents){
            money -= cost_in_cents;
            return 0;
        }
        money = 0;
        return cost_in_cents - money;
    }
    
    @Override
    public String returnFunds(){
        return "Your credit balance is " + money + " cents";
    }
}
