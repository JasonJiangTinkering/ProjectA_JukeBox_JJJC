import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import javafx.application.Application;
import javafx.stage.Stage;
import java.util.Scanner;
// This java class is only a helpful class meant to be held by a running program
public class SongPlayer extends Application{
    Media song;
    MediaPlayer mediaPlayer;
    purchaseQueue queue;

    @Override
    public void start(Stage stage){
        // line from java programing.mooc.fi / -> part 4 launch parameters.
        // lets me seperate the application from the launching in testGoalX.java
        String[] param = getParameters().getRaw().toArray(new String[0]);
        stage.setTitle("JukeBox Project A");
        
        // setup all the components
        SongList songs = new SongList(param[0]);
        balanceBox balance = new balanceBox();
        queue = new purchaseQueue(songs, balance);
        
        // show what songs are available
        System.out.println("Available Songs");
        System.out.println(songs.displaySongs());
        System.out.println();
        

        // WARNING JAVAFX SEEMS LIKE IT WONT BE ABLE TO TAKE TERMINAL INPUTS, 
        // ADD FUNDS FUNCTION MUST BE REFACTORED TO ACCEPT INPUTS FROM GUI FOR SUCCESS
// JUST SKIP BUYING SONGSS, JUST GO AND ADD IT TO THE QUEUE

        queue.addSongForFree(0);
        queue.addSongForFree(1);
        queue.addSongForFree(2);
        queue.addSongForFree(3);


        // balance.addFunds();
        // System.out.println();
        
        // // buy songs with the "keep adding until enough" pattern
        // System.out.println("Purchasing songs...");
        
        // // Try to buy song 0 (Bohemian Rhapsody)
        // while (!queue.addSong(0)) {
        //     System.out.println("Not enough funds. Add more:");
        //     balance.addFunds();
        // }
        
        // // Try to buy song 1 (Imagine)
        // while (!queue.addSong(1)) {
        //     System.out.println("Not enough funds. Add more:");
        //     balance.addFunds();
        // }
        
        // // Try to buy song 2 (Billie Jean)
        // while (!queue.addSong(2)) {
        //     System.out.println("Not enough funds. Add more:");
        //     balance.addFunds();
        // }
        
        // System.out.println();
        
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
        // System.out.println("Please input number of song at a certain position, Type 0 for no removal...and press enter to submit. ");
        
        // Scanner in = new Scanner(System.in);
        // int choice = in.nextInt() -1;
        // assert (choice < queue.getQueueSize() && choice >=0) == true;
        queue.removeSong(1);

        // try to get a refund
        System.out.println("Test 6: Refund");
        System.out.println(queue.refund());
        System.out.println();

        if (queue.getQueueSize() > 0){
            popQueuePlaySong();
        }


// once song over, try to play next.
        System.out.println("All sync commands ran, wait for songs to play, for tests done!");

    }
// Idea of using recursion to generate media players over and over inspired by 
// stack overflow questionns 61296959
    public void endOfMediaNextSong(){
        if (queue.getQueueSize() > 0){
            popQueuePlaySong();
        }else{
            System.out.println("End of Queue Thank you.");
        }
// not learned in class yet, the double colon operator, in the case of [A]::[B]
// point at the method B in class A.
// This is a callback funnction, so when the media player is over, it can call that function and boom new song.
        
    }

    public void popQueuePlaySong(){
        if (mediaPlayer != null){
            mediaPlayer.dispose();
        }
        String x = queue.nextSong().toString();
        System.out.println(x);
        song = new Media(x);
        mediaPlayer = new MediaPlayer(song);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(this::endOfMediaNextSong);
        System.out.println(queue.displayQueue());

    }
}