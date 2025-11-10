import java.util.Scanner;

/**
 * Jukebox class that holds actually amount of money in system by adding or subtracting money
 * @author Jason Jiang, Joel Cipher
 * @version 1.0
 */

public class balanceBox {
    private final cashPayment cashPaymentInst = new cashPayment();
    private final creditPayment creditPaymentInst = new creditPayment();
    private int total_available_cents = 0; 
    
    public static void main(String[] args) {
        balanceBox b = new balanceBox();
        // test when you have more than enough + when you dont have enough.
        while (b.deductFunds(300)){
            b.addFunds();
        }
        System.out.println("Item dispensed, was the last payment added, make value go over 300");

    
    }

    public void addFunds(){
        System.out.println("Add funds.\nEnter 1 for cash payment.\nEnter 2 for credit payment.\n:>");
        Scanner in = new Scanner(System.in);
        char choice = in.next().charAt(0);
        in.close();
        switch (choice){
            case '1':
                total_available_cents += cashPaymentInst.takePayment();
            case '2':
                total_available_cents += creditPaymentInst.takePayment();
        }
    }

    public Boolean deductFunds(int cost_in_cents){
        if (total_available_cents >= cost_in_cents){
            total_available_cents -= cost_in_cents;
            // try to subtract from pool of cash balance
            cost_in_cents = cashPaymentInst.subtractCertainBalance(cost_in_cents);
            cost_in_cents = creditPaymentInst.subtractCertainBalance(cost_in_cents);
            if (cost_in_cents>0){
                System.out.println("Error: Sum of Coin + Cash balances both are capable and uncapable of paying balance.");
            }
            return true;
        }
        return false;
    }
}
