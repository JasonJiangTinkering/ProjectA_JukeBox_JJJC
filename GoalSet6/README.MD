Goal set #5
Requirements
1. JavaFX SDK configured (e.g., PATH_TO_FX or module-path to javafx-sdk-21.0.9/lib).
2. songs.txt present in the same folder as the JAR (used by SongList).

How to run GUI:
- Build or use testGoal5.jar
- Run:
java --module-path "<your-path>/javafx-sdk-21.0.9/lib" --add-modules=javafx.controls,javafx.media -jar testGoal5.jar

What this Goal 5 GUI does:
- Shows “Sort by” Title/Artist radio buttons (no sorting yet).
- Loads song titles from songs.txt into a ListView.
- Has a “Now Playing” label at the bottom.
- Enqueue adds the selected song to purchaseQueue and prints the queue.
- Enqueue Front / Add Coin / Add Credit exist and just print messages for now.

Goal set #4
Requirements
1. JavaFX configured with an enviormental variable:
PATH_TO_FX
2. Include these songs in media folder
../media/happy_birthday.mp3
../media/chinese_happy_birthday.mp3

How to run Tests:
-make goalset4_jar
-Add coins / credi until you can pay for a song
-Type number you want
-Add to queue
-Wait to see if 2 songs play back to back

Goal set #2
How to run tests:
make goalset2_jar

Goal Set 1: Songs
How to run:
java -jar testGoal1.jar songs.txt