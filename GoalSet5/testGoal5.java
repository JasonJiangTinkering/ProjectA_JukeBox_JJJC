import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Goal 5 - JavaFX GUI for the Jukebox project.
 * Sets up the main window and starts wiring in
 * the classes from the earlier goals.
 * 
 * @author Joel Cipher
 * @version 1.0
 */
public class testGoal5 extends Application {

    // main project classes that this GUI will talk to
    private SongList songList;
    private balanceBox balance;
    private purchaseQueue queue;

    @Override
    public void start(Stage primaryStage) {
        // basic setup of the core classes
        songList = new SongList("songs.txt");
        balance = new balanceBox();
        queue = new purchaseQueue(songList, balance);

        // main layout
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        // top: sort radio buttons
        HBox topBar = new HBox(10);
        topBar.setPadding(new Insets(5));
        topBar.setAlignment(Pos.CENTER_LEFT);

        ToggleGroup sortGroup = new ToggleGroup();

        RadioButton sortByTitle = new RadioButton("Title");
        sortByTitle.setToggleGroup(sortGroup);
        sortByTitle.setSelected(true); // default

        RadioButton sortByArtist = new RadioButton("Artist");
        sortByArtist.setToggleGroup(sortGroup);

        RadioButton sortByTime = new RadioButton("Time");
        sortByTime.setToggleGroup(sortGroup);

        topBar.getChildren().addAll(sortByTitle, sortByArtist, sortByTime);
        root.setTop(topBar);

        // center: list of songs
        ObservableList<String> songItems = FXCollections.observableArrayList();

        // get the raw String[][] from SongList
        String[][] songs = songList.getSongArray();

        // assuming each song row has [0]=title, [1]=artist, [2]=time
        for (int i = 0; i < songs.length; i++) {
            if (songs[i] != null && songs[i][0] != null) {
                String title = songs[i][0];
                String artist = songs[i][1];
                String entry = title + " - " + artist;
                songItems.add(entry);
            }
        }

        ListView<String> songListView = new ListView<>(songItems);
        root.setCenter(songListView);

        // bottom: now playing + buttons
        HBox bottomBar = new HBox(10);
        bottomBar.setPadding(new Insets(5));
        bottomBar.setAlignment(Pos.CENTER_LEFT);

        Label nowPlayingLabel = new Label("Now Playing: (none)");

        Button enqueueButton = new Button("Enqueue");
        Button enqueueFrontButton = new Button("Enqueue Front");
        Button addCoinButton = new Button("Add Coin");
        Button addCreditButton = new Button("Add Credit");

        // basic button behavior for now (Goal 5 level)
        enqueueButton.setOnAction(e -> {
            int selectedIndex = songListView.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                System.out.println("Enqueue clicked for index: " + selectedIndex);
                // later: call queue with the selected song when Goal 6 is ready
            } else {
                System.out.println("No song selected to enqueue.");
            }
        });

        addCoinButton.setOnAction(e -> {
            System.out.println("Add Coin clicked");
            // later: balance.addCoin(); and maybe update a label
        });

        // others can stay as stubs for now
        enqueueFrontButton.setOnAction(e -> {
            System.out.println("Enqueue Front clicked (not implemented yet).");
        });

        addCreditButton.setOnAction(e -> {
            System.out.println("Add Credit clicked (not implemented yet).");
        });

        bottomBar.getChildren().addAll(
            nowPlayingLabel,
            enqueueButton,
            enqueueFrontButton,
            addCoinButton,
            addCreditButton
        );
        root.setBottom(bottomBar);

        // scene and window setup
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Jukebox");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Launches the JavaFX application.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
