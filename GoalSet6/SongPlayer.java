import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import javafx.application.Application;
import javafx.stage.Stage;
import java.util.Scanner;
import javafx.scene.control.Label;

/**
 * 
 * Holds the song player that plays media for the jukebox 
 * 
 * @author Jason Jiang
 * @version 1.0
 * */
// This java class is only a helpful class meant to be held by a running program
public class SongPlayer{
    Media song;
    MediaPlayer mediaPlayer;
    purchaseQueue queue;
    Label nowPlayingLabel;
    Boolean isPlaying = false;
    String songName;
    public SongPlayer(purchaseQueue q, Label l){
        this.queue = q;
        nowPlayingLabel = l;
    }
    
// Idea of using callback to generate media players over and over inspired by 
// stack overflow questionns 61296959
/**
 * This function gets run when the media is done playing, tries to play the next song on the queue
 * 
 */
    public void endOfMediaNextSong(){
        System.out.println("Finished song");
        if (queue.getQueueSize() > 0){
        System.out.println("Sng Start");
            
            popQueuePlaySong();
        }else{
            isPlaying = false;
            nowPlayingLabel.setText("End of Queue Thank you.");

            if (mediaPlayer != null){
            mediaPlayer.dispose();
            }
        }
        }
// not learned in class yet, the double colon operator, in the case of [A]::[B]
// point at the method B in class A.
// This is a callback funnction, so when the media player is over, it can call that function and boom new song.
        
    
/**
 * This attempts to start playing from the queue, but only if the media is not already playing
 * @return boolean, wether it was successful in starting the media playing from queue.
 */
    public boolean start_queue(){
        if (!isPlaying && queue.getQueueSize() >0){
            popQueuePlaySong();
            return true;
        }
        return false;
    }

/**This is the main way this player plays media, and updates the ui to show that media is playing.
 * 
 * @return weather its playing or not
 */
    public boolean popQueuePlaySong(){
        isPlaying = true;
        if (mediaPlayer != null){
            mediaPlayer.dispose();
        }
        String x = queue.nextSong().toString();
        nowPlayingLabel.setText("Now Playing: "+queue.getPlayingSongName()+"\n" + queue.displayQueue());
        System.out.println("Playing next song: "+x);
        song = new Media(x);
        mediaPlayer = new MediaPlayer(song);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(this::endOfMediaNextSong);
        System.out.println(queue.displayQueue());
        return true;
    }
}