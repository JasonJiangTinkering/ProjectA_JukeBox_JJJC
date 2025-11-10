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
        System.out.print("Enter a coin ('p', 'n', 'd', 'q', 'h', 'g')");
        char coinLetter = in.next().charAt(0);
        in.close();
        switch (coinLetter){
            case 'V':
                System.out.println("You have decided to vend");
                break;
            default:
                changedValue = coinBox.acceptCoin(coinLetter);
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