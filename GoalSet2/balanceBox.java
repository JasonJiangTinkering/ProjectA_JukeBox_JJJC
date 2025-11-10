package GoalSet2;
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
        System.out.println("Display Songs: choose from 1 - " + songArray.length + " then hit enter");
        System.out.println(songList.displaySongs());
        Scanner in = new Scanner(System.in);
        int choice = Integer.getInteger(in.nextLine());
        assert (choice-1 < songArray.length && choice-1 >=0) == true;
        while (!b.deductFunds(Integer.getInteger(songArray[choice][2]))){
            b.addFunds();
        }

    }
        
    

    public void addFunds(){
        System.out.println("Add funds.\nType 1 + Enter for cash payment.\nType 2 + Enter for credit payment.\n:>");
        Scanner in = new Scanner(System.in);
        char choice = in.nextLine().charAt(0);
        switch (choice){
            case '1':
                total_available_cents += cashPaymentInst.takePayment();
                break;
            case '2':
                total_available_cents += creditPaymentInst.takePayment();
                break;
            default:
                System.out.println("Please pick either 1 or 2");
        }
        System.out.printf("Your balance is $%.2f%n", ((double)total_available_cents) /100);
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
