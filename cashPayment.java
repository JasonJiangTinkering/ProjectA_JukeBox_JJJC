import java.util.Scanner;

/**
 * Jukebox cash payment
 * @author Jason Jiang, Joel Cipher
 * @version 1.0
 */

class cashPayment implements paymentLauncher{

    final private JukeBoxCoinBox coinBox = new JukeBoxCoinBox();
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
        return changedValue;
        
    }
    public int subtractCertainBalance(int cost_in_cents){
        return coinBox.subtractCertainBalance(cost_in_cents);
    }
    @Override
    public String returnFunds(){
        return coinBox.dispenseChange();
    }

} 