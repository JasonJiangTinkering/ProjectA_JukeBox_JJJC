import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import javafx.application.Application;
import javafx.stage.Stage;
public class SongPlayer extends Application{

    
    // queue
    public static void main(String[] args){
        launch(args);
        
    }

    @Override
    public void start(Stage stage){
        stage.setTitle("Goalset4: Testing Music");
        String  songName = "media/happy_birthday.mp3";
        Media song = new Media(new File(songName).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(song);
        
        popQueuePlaySong(mediaPlayer, song);

    }

    public static void popQueuePlaySong(MediaPlayer mediaPlayer, Media song){
        String  songName = "media/happy_birthday.mp3";
        song = new Media(new File(songName).toURI().toString());
        mediaPlayer = new MediaPlayer(song);
        
        System.out.println("playing song");
        mediaPlayer.play();
    }
}