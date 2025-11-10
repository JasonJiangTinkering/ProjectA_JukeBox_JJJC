
import java.io.*;
import java.net.URI;
import java.util.Scanner;

 /**
 * SongList class manages a collection of songs available for purchase.
 * Reads song data from a file and provides access to song information.
 * @author Joel Cipher
 * @version 1.0
 */
public class SongList {
    private Song[] songs;
    private int songCount;
    
    /**
     * Inner class represents a single song
     */
    private class Song {
        String title;
        String artist;
        double cost;
        String fileName;
        
        Song(String title, String artist, double cost, String fileName) {
            this.title = title;
            this.artist = artist;
            this.cost = cost;
            this.fileName = fileName;
        }
    }
    
    /**
     * Constructor loads songs from a data file
     * @param dataFileName is the path to the data file
     */
    public SongList(String dataFileName) {
        songCount = 0;
        // Count the lines for array sizing
        int lineCount = countLines(dataFileName);
        songs = new Song[lineCount];
        
        // Load the songs
        loadSongs(dataFileName);
    }
    
    /**
     * Counts the number of lines in the data file
     * @param dataFileName is the path to the data file
     * @return the number of lines
     */
    private int countLines(String dataFileName) {
        int count = 0;
        try {
            Scanner scanner = new Scanner(new File(dataFileName));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.isEmpty()) {
                    count++;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found - " + e.getMessage());
        }
        return count;
    }
    
    /**
     * Loads songs from the data file into the songs array
     * Expected format: title,artist,cost,local_file_name
     * @param dataFileName is the path to the data file
     */
    private void loadSongs(String dataFileName) {
        try {
            Scanner scanner = new Scanner(new File(dataFileName));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                
                // Skip empty lines
                if (line.isEmpty()) {
                    continue;
                }
                
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String title = parts[0];
                    String artist = parts[1];
                    double cost = Double.parseDouble(parts[2]);
                    String fileName = parts[3];
                    
                    songs[songCount] = new Song(title, artist, cost, fileName);
                    songCount++;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found - " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing cost: " + e.getMessage());
        }
    }
    
    /**
     * Returns a doubly-subscripted array containing song details
     * @return String[][] where each row contains title, artist, and cost
     */
    public String[][] getSongArray() {
        String[][] songArray = new String[songCount][3];
        
        for (int i = 0; i < songCount; i++) {
            songArray[i][0] = songs[i].title;
            songArray[i][1] = songs[i].artist;
            songArray[i][2] = String.format("%.2f", songs[i].cost);
        }
        
        return songArray;
    }
    
    /**
     * Returns the URI for a song at the specified index
     * @param index the index of the song
     * @return URI for the song file location
     */
    public URI getSongURI(int index) {
        if (index < 0 || index >= songCount) {
            return null;
        }
        
        // Create URI with file:// protocol for local files
        return URI.create("file://./" + songs[index].fileName);
    }
    
    /**
     * Returns the total number of songs
     * @return the total number of songs
     */
    public int getSongCount() {
        return songCount;
    }
    
    /**
     * Returns a formatted string of all songs for display
     * @return String containing all songs formatted for display
     */
    public String displaySongs() {
        String result = "";
        
        // Add header
        result += String.format("%-5s %-30s %-30s %-10s%n", "Index", "Title", "Artist", "Cost");
        result += "-".repeat(80) + "\n";
        
        // Add each song
        for (int i = 0; i < songCount; i++) {
            result += String.format("%-5d %-30s %-30s $%-9.2f%n", 
                i, songs[i].title, songs[i].artist, songs[i].cost);
        }
        
        return result;
    }
}