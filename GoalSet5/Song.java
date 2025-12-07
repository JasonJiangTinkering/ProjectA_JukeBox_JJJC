/**
 * Simple Song class so the GUI can show titles and artists.
 * This can be replaced or expanded later if needed.
 */
public class Song {
    private String title;
    private String artist;
    private int playTime;

    public Song(String title, String artist, int playTime) {
        this.title = title;
        this.artist = artist;
        this.playTime = playTime;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getPlayTime() {
        return playTime;
    }

    @Override
    public String toString() {
        return title + " - " + artist + " (" + playTime + "s)";
    }
}

