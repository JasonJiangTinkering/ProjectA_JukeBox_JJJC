import java.util.Scanner;

/**
 * Jukebox cash payment
 * @author Jason Jiang
 * @version 1.0
 */

class cashPayment implements paymentLauncher{

    final private JukeBoxCoinBox coinBox = new JukeBoxCoinBox();
    /**
     * prompts user to insert coin
     * @return updated balance  of user
     */
    @Override
    public int takePayment(){
        Scanner in = new Scanner(System.in);
        int changedValue = 0;
        //System.out.println(coinLetter);
        System.out.print("Type a coin and hit enter ('p', 'n', 'd', 'q', 'h', 'g')");
        char choice = in.nextLine().charAt(0);
        switch (choice){
            case 'V':
                System.out.println("You have decided to vend");
                break;
            default:
                changedValue = coinBox.acceptCoin(choice);
        }
        // Balance
        // System.out.printf("Your balance is $%.2f%n", money);
        in.close();
        return changedValue;
        
    }
/**
 * Takes coin and addes coin to balance
 * @return changed balance of user
 * @param c character that represent coin inserted
 */
    public int takePayment(char c){
        return coinBox.acceptCoin(c);
    }
/**
 * Trys to remove coins from system equivelent  to cost in cents
 * @param cost_in_cents amount of money trying to subtract
 * @return updated balance of user
 */
    // returns money subtracted from cost_in_cents
    public int subtractCertainBalance(int cost_in_cents){
        return coinBox.subtractCertainBalance(cost_in_cents);
    }
    /**
     * @return String that returns all coins back to user if it can.
     */
    @Override
    public String returnFunds(){
        return coinBox.dispenseChange();
    }

} 