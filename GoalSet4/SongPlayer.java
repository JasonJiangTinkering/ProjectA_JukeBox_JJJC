import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import javafx.application.Application;
import javafx.stage.Stage;
// This java class is only a helpful class meant to be held by a running program
public class SongPlayer extends Application{
    Media song;
    MediaPlayer mediaPlayer;

    @Override
    public void start(Stage stage){
        stage.setTitle("Goalset4: Testing Music");
        popQueuePlaySong("media/happy_birthday.mp3");
    }

    public void popQueuePlaySong(String songName){
        song = new Media(new File(songName).toURI().toString());
        mediaPlayer = new MediaPlayer(song);
        
        System.out.println("playing song");
        mediaPlayer.play();
    }
}