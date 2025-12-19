import java.util.Scanner;

/**
 * Jukebox class that holds actually amount of money in system by adding or subtracting money
 * @author Jason Jiang
 * @version 1.0
 */

public class balanceBox {
    private final cashPayment cashPaymentInst = new cashPayment();
    private final creditPayment creditPaymentInst = new creditPayment();
    private int total_available_cents = 0; 

/**
 * Holds the correct money that the user has at the time, stores the instatnces that hold the credit card and cash balance.
 */
    public balanceBox(){

    }

    /**
     * testing function
     * @hidden 
     */
    public static void main(String[] args) {
        balanceBox b = new balanceBox();
        System.out.println("Test 1: That add and deduct funds were working. Trying to reach 300");
        // test when you have more than enough + when you dont have enough.
        // That add and deduct funds were working.
        while (!b.deductFunds(300)){
            b.addFunds();
        }
        System.out.println("Item dispensed, was the last payment added, make value go over 300");

        System.out.println("Test 2: Choosing a song, and depending on that song allowing customer to add payments to make value go over 300 , then dispensing change");

        SongList songList = new SongList("songs.txt");
        String[][] songArray = songList.getSongArray();
        System.out.println("Display Songs: choose from 0 - " + (songArray.length-1) + " then hit enter");
        System.out.println(songList.displaySongs());
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        assert (choice-1 < songArray.length && choice-1 >=0) == true;
        while (!b.deductFunds((int)Double.parseDouble(songArray[choice][2]) * 100)){
            b.addFunds();
        }

    }

/**
 * returns balance of user in cents
 * @return balance of user in cents
 */
    public int get_available_cents(){
        return total_available_cents;
    }
        
   /** 
    * Adds balance equal to coin
    * Possible coins: 'p', 'n', 'd', 'q', 'h', 'g'
    * @param c character that matches a  possible coin
    * */ 
    public void addCoin(char c){
        total_available_cents += cashPaymentInst.takePayment(c);
    }
    /**
     * Adds 500 cents to balance, simulating credit card swipe
     */
    public void addFiveDollarCredit(){
        total_available_cents += creditPaymentInst.takePayment(500);
    }
/**
 * Prompts user to add cash or coins
 */
    public void addFunds(){
        System.out.println("Add funds.\nType 1 + Enter for cash payment.\nType 2 + Enter for credit payment.\n:>");
        Scanner in = new Scanner(System.in);
        String choice = in.nextLine();
        switch (choice.strip()){
            case "1":
                total_available_cents += cashPaymentInst.takePayment();
                break;
            case "2":
                total_available_cents += creditPaymentInst.takePayment();
                break;
            default:
                System.out.println("Please pick either 1 or 2");
        }
        System.out.printf("Your balance is $%.2f%n", ((double)total_available_cents) /100);
    }
/**
 * deducts funds from user balance, does not take away funds from the coin or credit balance
 * Just the overall count, that is relied  upon by other classes
 * @param cost_in_cents cents to deduct from user  balance
 * @return true if deducted funds successful
 */
    public Boolean deductFunds(int cost_in_cents){
        /* 
        if (total_available_cents >= cost_in_cents){
            // try to subtract from pool of cash balance
            int subtractedAmount = cashPaymentInst.subtractCertainBalance(cost_in_cents);
            cost_in_cents -= subtractedAmount;
            total_available_cents -= subtractedAmount;
            subtractedAmount = creditPaymentInst.subtractCertainBalance(cost_in_cents);
            cost_in_cents -= subtractedAmount;
            total_available_cents -= subtractedAmount;
            if (cost_in_cents>0){
                System.out.println("Error: Sum of Coin + Cash balances both are capable and uncapable of paying balance.");
            }
            System.out.println("Thanks for the purchase");
            System.out.println("Your remaining balance is $"+ String.format("%.2f", ((double)total_available_cents)/100));
            return true;
        }
        return false;
        */
        if (total_available_cents >= cost_in_cents){
            int originalCost = cost_in_cents;

            // try cash
            int subtractedAmount = cashPaymentInst.subtractCertainBalance(cost_in_cents);
            cost_in_cents -= subtractedAmount;

            // try credit
            subtractedAmount = creditPaymentInst.subtractCertainBalance(cost_in_cents);
            cost_in_cents -= subtractedAmount;

            // if not fully paid, fail WITHOUT changing total_available_cents
            if (cost_in_cents > 0){
                System.out.println("Error: Sum of Coin + Cash balances could not fully pay balance.");
                return false;
            }

            // success: now subtract from total_available_cents
            total_available_cents -= originalCost;
            System.out.println("Thanks for the purchase");
            System.out.println("Your remaining balance is $" +
                String.format("%.2f", ((double)total_available_cents)/100));
            return true;
        }
        return false;
    }

    /**
     * Returns leftover money to the user
     * @return message about the refund
     */
    public String returnFunds() {
        // get refund info from both payment types
        String cashRefund = cashPaymentInst.returnFunds();
        String creditRefund = creditPaymentInst.returnFunds();
        
        // convert cents back to dollars
        double totalDollars = total_available_cents / 100.0;
        
        String message = "Refunding your balance: $" + String.format("%.2f", totalDollars) + "\n";
        message = message + cashRefund + "\n" + creditRefund;
        
        // clear out the balance
        total_available_cents = 0;
        
        return message;
    }
}


