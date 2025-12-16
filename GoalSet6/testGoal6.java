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
public class testGoal6 extends Application {

    // main project classes that this GUI will talk to
    private SongList songList;
    private balanceBox balance;
    private purchaseQueue queue;
    private SongPlayer player;

    // label at the bottom to show which song is playing
    private Label nowPlayingLabel;

    @Override
    public void start(Stage primaryStage) {

        // basic setup of the core classes
        balance = new balanceBox();
        songList = new SongList("songs.txt");
        queue = new purchaseQueue(songList, balance);

        // main layout
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        // top: sort radio buttons
        HBox topBar = new HBox(10);
        topBar.setPadding(new Insets(5));
        topBar.setAlignment(Pos.CENTER_LEFT);

        ToggleGroup sortGroup = new ToggleGroup();

        // sort options (no real sorting yet, just the UI for Goal 5)
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

        // assuming each song row has [0]=title, [1]=artist, [2]=cost
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

        nowPlayingLabel = new Label("Now Playing: (none)");
        player = new SongPlayer(queue, nowPlayingLabel);


        Button enqueueButton = new Button("Enqueue");
        Button enqueueFrontButton = new Button("Enqueue Front");

        Button addDollarButton = new Button("Dollar-Coin");
        Button addHalfDollarButton = new Button("Half-Dollar-Coin");
        Button addQuarterButton = new Button("Quarter-Coin");
        Button addDimeButton = new Button("Dime-Coin");
        Button addNickelButton = new Button("Nickel-Coin");
        Button addPennyButton = new Button("Penny-Coin");
        Button refundButton = new Button("Refund");
        Button addCreditButton = new Button("$5-Credit");

        // basic button behavior for now (Goal 5 level)
        // when user clicks Enqueue, try to add the selected song to the queue
        enqueueButton.setOnAction(e -> {
            int selectedIndex = songListView.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                boolean ok = queue.addSong(selectedIndex, false);
                if (player.start_queue()){
                    System.out.println("start queue ");
                }else{
                System.out.println("Could not start queue .");

                }
                System.out.println("Enqueue clicked for index: " + selectedIndex + ", success=" + ok);
                System.out.println(queue.displayQueue());
                // for Goal 5: just update the label to show whichever song was last selected
                String selectedText = songListView.getSelectionModel().getSelectedItem();
                nowPlayingLabel.setText(selectedText + " enqueued to Back\n" + "Now Playing: "+queue.getPlayingSongName()+"\n" + queue.displayQueue());

                
                
            } else {
                System.out.println("No song selected to enqueue.");
                nowPlayingLabel.setText("No song selected to enqueue.");
            }
        });
        enqueueFrontButton.setOnAction(e -> {
            int selectedIndex = songListView.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                boolean ok = queue.addSong(selectedIndex, true);
                if (player.start_queue()){
                    System.out.println("start queue ");
                }else{
                System.out.println("Could not start queue .");

                }
                System.out.println("Enqueue clicked for index: " + selectedIndex + ", success=" + ok);
                System.out.println(queue.displayQueue());
                // for Goal 5: just update the label to show whichever song was last selected
                String selectedText = songListView.getSelectionModel().getSelectedItem();
                nowPlayingLabel.setText(selectedText + " enqueued to Front\n" + "Now Playing: "+queue.getPlayingSongName()+"\n" + queue.displayQueue());
            } else {
                System.out.println("No song selected to enqueue.");
                nowPlayingLabel.setText("No song selected to enqueue.");
            }
        });
        // addCoinButton.setOnAction(e -> {
        //     System.out.println("Add Coin clicked");
        //     // For Goal 5, behavior can just be a stub print
        //     // Later goals: hook this into balanceBox for real payments
        // });

        addDollarButton.setOnAction(e ->{
            balance.addCoin('g');
            nowPlayingLabel.setText("Balance: " +balance.get_available_cents());
        });
        addHalfDollarButton.setOnAction(e ->{
            balance.addCoin('h');
            nowPlayingLabel.setText("Balance: " +balance.get_available_cents());
        });
        addQuarterButton.setOnAction(e ->{
            balance.addCoin('q');
            nowPlayingLabel.setText("Balance: " +balance.get_available_cents());
        });
        addDimeButton.setOnAction(e ->{
            balance.addCoin('d');
            nowPlayingLabel.setText("Balance: " +balance.get_available_cents());
        });
        addNickelButton.setOnAction(e ->{
            balance.addCoin('n');
            nowPlayingLabel.setText("Balance: " +balance.get_available_cents());
        });
        addPennyButton.setOnAction(e ->{
            balance.addCoin('p');
            nowPlayingLabel.setText("Balance: " +balance.get_available_cents());
        });

        addCreditButton.setOnAction(e -> {
            balance.addFiveDollarCredit();
            nowPlayingLabel.setText("Balance: " +balance.get_available_cents());
        });
        refundButton.setOnAction(e-> {
            nowPlayingLabel.setText("Returned Balance: " +balance.returnFunds());
        });

        bottomBar.getChildren().addAll(
                nowPlayingLabel,
                enqueueButton,
                enqueueFrontButton,
                addDollarButton,
                addHalfDollarButton,
                addDimeButton,
                addNickelButton,
                addPennyButton,
                addCreditButton,
                refundButton

        );

        root.setBottom(bottomBar);

        // scene and window setup
        Scene scene = new Scene(root, 1200, 600);
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


    // Useful compartmentization for interupt exception 
    // Got from stack overflow
    // question 24104313, how do i make a delay in java
    public static void pause(int ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e){
            System.err.format("InterruptedException : %s%n", e);
        }
    }
}
