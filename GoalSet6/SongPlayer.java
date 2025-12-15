import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import javafx.application.Application;
import javafx.stage.Stage;
import java.util.Scanner;
// This java class is only a helpful class meant to be held by a running program
public class SongPlayer{
    Media song;
    MediaPlayer mediaPlayer;
    purchaseQueue queue;
    String songName;
    public SongPlayer(purchaseQueue q){
        this.queue = q;
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

    public boolean start_queue(){
        if (mediaPlayer == null && queue.getQueueSize() >0){
            
            return popQueuePlaySong();
        }
    }

    public boolean popQueuePlaySong(){
        if (mediaPlayer != null){
            mediaPlayer.dispose();
            return false;
        }
        String x = queue.nextSong().toString();
        System.out.println(x);
        song = new Media(x);
        mediaPlayer = new MediaPlayer(song);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(this::endOfMediaNextSong);
        System.out.println(queue.displayQueue());
        return true;
    }
}