import java.net.URI;
import java.util.Deque;
import java.util.LinkedList;

/**
 * purchaseQueue - handles the queue of songs that have been purchased
 * Only lets you add songs if you have enough money
 * 
 * @author Joel Cipher
 * @version 1.0
 */
public class purchaseQueue {
    
    // using Deque like the project spec recommended
    private Deque<QueuedSong> queue;
    
    private SongList songList;
    private balanceBox balance;

    private String currentlyPlayingSongName;
    
    /**
     * Holds info about a song in the queue
     */
    private class QueuedSong {
        String title;
        String artist;
        double cost;
        int songIndex;
        
        /**
         * Creates a queued song
         */
        QueuedSong(String title, String artist, double cost, int songIndex) {
            this.title = title;
            this.artist = artist;
            this.cost = cost;
            this.songIndex = songIndex;
        }
    }
    
    /**
     * Sets up the queue
     * @param songList the list of available songs
     * @param balance the balance tracker
     */
    public purchaseQueue(SongList songList, balanceBox balance) {
        this.queue = new LinkedList<QueuedSong>();
        this.songList = songList;
        this.balance = balance;
    }

    String getPlayingSongName(){
        return currentlyPlayingSongName;
    }
// For testing purposes, add song without testing balance.
    Boolean addSongForFree(int songIndex) {
        String[][] songArray = songList.getSongArray();
        
        // check if the song number is valid
        if (songIndex < 0 || songIndex >= songArray.length) {
            System.out.println("Error: invalid song number");
            return false;
        }
        
        // get song details from the array
        String title = songArray[songIndex][0];
        String artist = songArray[songIndex][1];
        double cost = Double.parseDouble(songArray[songIndex][2]);
        
        // add the song to end of queue
        QueuedSong song = new QueuedSong(title, artist, cost, songIndex);
        queue.addLast(song);
        
        System.out.println("Added to queue: " + title + " by " + artist + " ($" + cost + ")");
        return true;
    }


    /**
     * Adds a song to the queue if there's enough money
     * @param songIndex which song to add (starts at 0)
     * @return true if added, false if not enough funds or bad index
     */
    public boolean addSong(int songIndex, boolean addFrontTrue) {
        String[][] songArray = songList.getSongArray();
        
        // check if the song number is valid
        if (songIndex < 0 || songIndex >= songArray.length) {
            System.out.println("Error: invalid song number");
            return false;
        }
        
        // get song details from the array
        String title = songArray[songIndex][0];
        String artist = songArray[songIndex][1];
        double cost = Double.parseDouble(songArray[songIndex][2]);
        
        // NOTE: balance uses cents so need to convert
        int costInCents = (int)(cost * 100);
        
        if (addFrontTrue){
            costInCents += 50;
        }
        // try to deduct the cost
        if (!balance.deductFunds(costInCents)) {
            System.out.println("Not enough money to buy: " + title);
            return false;
        }
        
        // add the song to end of queue
        QueuedSong song = new QueuedSong(title, artist, cost, songIndex);
        if (addFrontTrue){
            queue.addFirst(song);
        }else{
            queue.addLast(song);
        }
        
        
        System.out.println("Added to queue: " + title + " by " + artist + " ($" + cost + ")");
        return true;
    }

    
    
    /**
     * Removes a song from the queue at a specific position
     * @param index position in queue (0 = first song)
     * @return true if removed, false if bad index
     */
    public boolean removeSong(int index) {
        if (index < 0 || index >= queue.size()) {
            System.out.println("Error: invalid queue position");
            return false;
        }
        
        // had to convert to array to get by index since Deque doesn't have get()
        QueuedSong[] queueArray = queue.toArray(new QueuedSong[0]);
        QueuedSong removed = queueArray[index];
        
        queue.remove(removed);
        
        System.out.println("Removed from queue: " + removed.title + " by " + removed.artist);
        return true;
    }
    
    /**
     * Gets the next song to play and removes it from queue
     * @return URI of next song, or null if queue empty
     */
    public URI nextSong() {
        // check if queue is empty
        if (queue.size() == 0) {
            System.out.println("Queue is empty.");
            return null;
        }
        
        // take first song off the queue
        QueuedSong nextSong = queue.removeFirst();
        
        // get the URI for this song
        URI songURI = songList.getSongURI(nextSong.songIndex);
        
        currentlyPlayingSongName = nextSong.title;

        System.out.println("Now playing: " + nextSong.title + " by " + nextSong.artist);
        return songURI;
    }
    
    
    /**
     * Shows all songs in the queue
     * @return string with all queued songs
     */
    public String displayQueue() {
        if (queue.size() == 0) {
            return "Queue is empty.";
        }
        
        String result = "=== Current Queue ===\n";
        
        int position = 1;
        // loop through all songs in queue
        for (QueuedSong song : queue) {
            result = result + position + ". " + song.title + " by " + song.artist + 
                     " ($" + song.cost + ")\n";
            position++;
        }
        
        return result;
    }
    
    /**
     * Gets how many songs are in the queue
     * @return number of songs
     */
    public int getQueueSize() {
        return queue.size();
    }
    
    /**
     * Returns any leftover money
     * @return refund message
     */
    public String refund() {
        return balance.returnFunds();
    }
}

