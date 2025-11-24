import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

class SongPlayer{
    // queue
    public static void main(String[] args){
        popQueuePlaySong();
        // balanceBox b = new balanceBox();
        // System.out.println("Test 1: That add and deduct funds were working. Trying to reach 300");
        // // test when you have more than enough + when you dont have enough.
        // // That add and deduct funds were working.
        // while (!b.deductFunds(300)){
        //     b.addFunds();
        // }
        // System.out.println("Item dispensed, was the last payment added, make value go over 300");

        // System.out.println("Test 2: Choosing a song, and depending on that song allowing customer to add payments to make value go over 300 , then dispensing change");

        // SongList songList = new SongList("songs.txt");
        // String[][] songArray = songList.getSongArray();
        // System.out.println("Display Songs: choose from 0 - " + (songArray.length-1) + " then hit enter");
        // System.out.println(songList.displaySongs());
        // Scanner in = new Scanner(System.in);
        // int choice = in.nextInt();
        // assert (choice-1 < songArray.length && choice-1 >=0) == true;
        // while (!b.deductFunds((int)Double.parseDouble(songArray[choice][2]) * 100)){
        //     b.addFunds();
        // }
    }
    public static void popQueuePlaySong(){
        String  songPlay = "../media/happy_birthday.mp3";
        Media sound = new Media(new File(songPlay).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }
}