/**
 * Testing program for Goal 3 - Song Queue
 * Tests purchasing songs, managing the queue, and refunds
 * 
 * @author Joel Cipher
 * @version 1.0
 */
public class testGoal3 {
    
    public static void main(String[] args) {

        String songsFile = args[0];
        System.out.println("Loading songs from: " + songsFile);
        System.out.println();
        
        // setup all the components
        SongList songs = new SongList(songsFile);
        balanceBox balance = new balanceBox();
        purchaseQueue queue = new purchaseQueue(songs, balance);
        
        System.out.println("GOAL 3 TEST - Song Queue");
        System.out.println();
        
        // show what songs are available
        System.out.println("Available Songs");
        System.out.println(songs.displaySongs());
        System.out.println();
        
        // try buying a song without any money (should fail)
        System.out.println("Test 1: Try to buy without money");
        System.out.println("Attempting to add song 0...");
        queue.addSong(0);
        System.out.println();
        
        // add some money
        System.out.println("Test 2: Add money and buy songs");
        System.out.println("Adding funds...");
        balance.addFunds();
        System.out.println();
        
        // buy a few songs
        System.out.println("Purchasing songs...");
        queue.addSong(0);  // Bohemian Rhapsody
        queue.addSong(1);  // Imagine
        queue.addSong(2);  // Billie Jean
        System.out.println();
        
        // show the queue
        System.out.println("Test 3: Display Queue");
        System.out.println(queue.displayQueue());
        System.out.println();
        
        // play the next song
        System.out.println("Test 4: Play next song");
        queue.nextSong();
        System.out.println("Queue after playing:");
        System.out.println(queue.displayQueue());
        System.out.println();
        
        // remove a song from the middle
        System.out.println("Test 5: Remove a song");
        System.out.println("Removing song at position 0...");
        queue.removeSong(0);
        System.out.println("Queue after removal:");
        System.out.println(queue.displayQueue());
        System.out.println();
        
        // try to get a refund
        System.out.println("Test 6: Refund");
        System.out.println(queue.refund());
        System.out.println();
        
        System.out.println("All tests done!");
    }
}

