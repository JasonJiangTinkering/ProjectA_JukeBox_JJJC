# Goal set 4
# Make sure to run makefile with your own .env file
include .env
goalset4_runjava: goalset4_compilejava GoalSet4.class 
	java --module-path ${sdkPath}/lib --add-modules javafx.controls,javafx.media,javafx.graphics GoalSet4

goalset4_compilejava: GoalSet4.java .env
	find *.java > temp.txt
	javac --module-path ${sdkPath}/lib --add-modules javafx.controls,javafx.media,javafx.graphics @temp.txt


# Goal Set 2

goalset2_makejar:
	jar cvfe testGoal2.jar balanceBox *.class *.java

goalset2_runjar: testGoal2.jar
	java -jar testGoal2.jar

goalset2_jar:  goalset2_compilejava goalset2_makejar goalset2_runjar
goalset2_compilejava:  balanceBox.java
	find *.java > temp.txt
	javac @temp.txt
	javac balanceBox.java 
	

goalset2_runjava: balanceBox.class
	java balanceBox

goalset2_run: goalset2_compilejava goalset2_runjava
	

