import java.net.URI;

/**
 * Test program for Goal 1: Songs
 * Tests the SongList class functionality
 * @author Joel Cipher
 * @version 1.0
 */
public class testGoal1 {
    public static void main(String[] args) {

        // Check for data file argument
        String dataFileName = args[0];
        System.out.println("Loading songs from: " + dataFileName);
        System.out.println();
        
        // Create SongList instance
        SongList songList = new SongList(dataFileName);
        
        // Test 1: Display all songs
        System.out.println("TEST 1: Display Songs");
        System.out.println(songList.displaySongs());
        System.out.println();
        
        // Test 2: Get song array
        System.out.println("TEST 2: Get Song Array");
        String[][] songArray = songList.getSongArray();
        System.out.println("Number of songs: " + songArray.length);

        for (int i = 0; i < songArray.length; i++) {
            System.out.printf("Song %d: Title='%s', Artist='%s', Cost='%s'%n", 
                i, songArray[i][0], songArray[i][1], songArray[i][2]);
        }
        System.out.println();
        
        // Test 3: Get URIs for each song
        System.out.println("TEST 3: Get Song URIs");
        for (int i = 0; i < songList.getSongCount(); i++) {
            URI uri = songList.getSongURI(i);
            System.out.printf("Song %d URI: %s%n", i, uri);
        }
        System.out.println();
    }
}

