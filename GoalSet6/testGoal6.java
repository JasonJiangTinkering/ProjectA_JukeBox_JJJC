import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
 * Goal 6 - JavaFX GUI for the Jukebox project.
 * Implements sorting, song playing, and payment system
 * @author Jason Jiang
 * @version 1.0
 */
public class testGoal6 extends Application {

    // main project classes that this GUI will talk to
    private SongList songList;
    private balanceBox balance;
    private purchaseQueue queue;
    private SongPlayer player;
    BorderPane root;
    int[] sortedSongsIndexs;
    // label at the bottom to show which song is playing
    private Label nowPlayingLabel;
    private Label moneyLeft;
    ListView<String> songListView;
    void bubbleSortInPlace(String[][]songItems, int[] sortedSongsIndexs, int heristic){
        boolean sorted=false;
        while (!sorted){
            boolean roundsorted=true;
            for (int i =0; i< sortedSongsIndexs.length-1; i++){
                String w1 = songItems[sortedSongsIndexs[i]][heristic];
                String w2 = songItems[sortedSongsIndexs[i+1]][heristic];
                System.out.println(w1+w2);
                for (int heristicCharacter = 0;heristicCharacter<Math.min(w1.length(), w2.length());heristicCharacter++){
                    if (w1.charAt(heristicCharacter) < w2.charAt(heristicCharacter)){
                        break;
                    }
                    if (w1.charAt(heristicCharacter) > w2.charAt(heristicCharacter)){
                        System.out.println("Swapping");
                        int temp = sortedSongsIndexs[i];
                        sortedSongsIndexs[i] = sortedSongsIndexs[i+1];
                        sortedSongsIndexs[i+1] = temp;
                        roundsorted = false;
                        break;
                    }                            
                }


            }
            sorted = roundsorted;
        }

    }
/**
 * Internal function that gets called when the list of songs needs to be regenerated after sorting on different param.
 * @param selectedIndex, the attribute of the song to sort betwee
 * 0-> is the song title, 1-> artist name
 */
    void renderSongs(int selectedIndex){

        // center: list of songs
        ObservableList<String> songItems = FXCollections.observableArrayList();

        // get the raw String[][] from SongList
        String[][] songs = songList.getSongArray();

        // assuming each song row has [0]=title, [1]=artist
        sortedSongsIndexs = new int[songs.length];
        for (int i = 0; i < songs.length; i++) {
            sortedSongsIndexs[i] = i;
        }


        bubbleSortInPlace(songs, sortedSongsIndexs, selectedIndex);

        for (int i: sortedSongsIndexs){
            if (songs[i] != null && songs[i][0] != null) {
                String title = songs[i][0];
                String artist = songs[i][1];
                String cost = songs[i][2];
                String entry = title + " - " + artist+ " - Cost To queue: " + cost +"- Cost to play next: " + (Double.parseDouble(cost) +0.50) ;
                songItems.add(entry);
            }
        }

        songListView = new ListView<>(songItems);
        root.setCenter(songListView);
        System.out.println("rendingSong");
    }

    @Override
    public void start(Stage primaryStage) {

        // basic setup of the core classes
        balance = new balanceBox();
        songList = new SongList("songs.txt");
        queue = new purchaseQueue(songList, balance);

        // main layout
        root = new BorderPane();
        root.setPadding(new Insets(10));

        // top: sort radio buttons
        HBox topBar = new HBox(10);
        topBar.setPadding(new Insets(5));
        topBar.setAlignment(Pos.CENTER_LEFT);

        ToggleGroup sortGroup = new ToggleGroup();

        // sort options (no real sorting yet, just the UI for Goal 5)
        RadioButton sortByTitle = new RadioButton("Title");
        sortByTitle.setToggleGroup(sortGroup);
        sortByTitle.setOnAction(event->{
            if(sortByTitle.isSelected())
            renderSongs(0);
        });
        sortByTitle.setSelected(true); // default

        RadioButton sortByArtist = new RadioButton("Artist");
        sortByArtist.setToggleGroup(sortGroup);
        sortByArtist.setOnAction(event->{            
            if(sortByArtist.isSelected())
            renderSongs(1);
        }); 

        topBar.getChildren().addAll(sortByTitle, sortByArtist);
        root.setTop(topBar);
        renderSongs(0);


        // bottom: now playing + buttons
        HBox bottomBar = new HBox(10);
        HBox bottomTopBar = new HBox(10);
        VBox vBottomBar = new VBox(10);
        bottomBar.setPadding(new Insets(5));
        bottomBar.setAlignment(Pos.CENTER_LEFT);

        nowPlayingLabel = new Label("Now Playing: (none)");
        moneyLeft = new Label("Money: 0");
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
                boolean ok = queue.addSong(sortedSongsIndexs[selectedIndex], false);
                if (player.start_queue()){
                    System.out.println("start queue ");
                }else{
                    System.out.println("Could not start queue .");
                }
                moneyLeft.setText("Money: "+balance.get_available_cents());
                
                System.out.println("Enqueue clicked for index: " + selectedIndex + ", success=" + ok);
                System.out.println(queue.displayQueue());
                // for Goal 5: just update the label to show whichever song was last selected
                String selectedText = songListView.getSelectionModel().getSelectedItem();
                
                if (!ok){
                    nowPlayingLabel.setText("Could not pay for song\n" + "Now Playing: "+queue.getPlayingSongName()+"\n" + queue.displayQueue());
                    return;
                }
                nowPlayingLabel.setText(selectedText + " enqueued to Back\n" + "Now Playing: "+queue.getPlayingSongName()+"\n" + queue.displayQueue());

                
                
            } else {
                System.out.println("No song selected to enqueue.");
                nowPlayingLabel.setText("No song selected to enqueue.");
            }
        });
        enqueueFrontButton.setOnAction(e -> {
            int selectedIndex = songListView.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                
                boolean ok = queue.addSong(sortedSongsIndexs[selectedIndex], true);

                if (player.start_queue()){
                    System.out.println("start queue ");
                }else{
                    System.out.println("Could not start queue .");

                }
                System.out.println("Enqueue clicked for index: " + selectedIndex + ", success=" + ok);
                System.out.println(queue.displayQueue());
                // for Goal 5: just update the label to show whichever song was last selected
                
                 if (!ok){
                    nowPlayingLabel.setText("Could not pay for song\n" + "Now Playing: "+queue.getPlayingSongName()+"\n" + queue.displayQueue());
                    return;
                }
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
            moneyLeft.setText("Balance: " +balance.get_available_cents());
        });
        addHalfDollarButton.setOnAction(e ->{
            balance.addCoin('h');
            moneyLeft.setText("Balance: " +balance.get_available_cents());
        });
        addQuarterButton.setOnAction(e ->{
            balance.addCoin('q');
            moneyLeft.setText("Balance: " +balance.get_available_cents());
        });
        addDimeButton.setOnAction(e ->{
            balance.addCoin('d');
            moneyLeft.setText("Balance: " +balance.get_available_cents());
        });
        addNickelButton.setOnAction(e ->{
            balance.addCoin('n');
            moneyLeft.setText("Balance: " +balance.get_available_cents());
        });
        addPennyButton.setOnAction(e ->{
            balance.addCoin('p');
            moneyLeft.setText("Balance: " +balance.get_available_cents());
        });

        addCreditButton.setOnAction(e -> {
            balance.addFiveDollarCredit();
            moneyLeft.setText("Balance: " +balance.get_available_cents());
        });
        refundButton.setOnAction(e-> {
            moneyLeft.setText("Returned Balance: " +balance.returnFunds());
        });
        bottomTopBar.getChildren().addAll(
            moneyLeft,
            addDollarButton,
            addHalfDollarButton,
            addDimeButton,
            addNickelButton,
            addPennyButton
        );
        bottomBar.getChildren().addAll(
            nowPlayingLabel,
            enqueueButton,
            enqueueFrontButton,
            addCreditButton,
            refundButton

        );
        vBottomBar.getChildren().addAll(bottomTopBar, bottomBar);

        root.setBottom(vBottomBar);

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


}
