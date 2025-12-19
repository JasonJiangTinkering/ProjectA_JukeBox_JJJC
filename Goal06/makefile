# Goal set 4
# Make sure to run makefile with your own .env file
include .env

cleanJavaDoc:
	rm -r ../docs/*

goalset6_javadoc:
	javadoc --module-path ${sdkPath}/lib --add-modules javafx.controls,javafx.media,javafx.graphics *.java -linksource -d ../docs
goalset6_runjava: goalset6_compilejava testGoal6.class 
	java --module-path ${sdkPath}/lib --add-modules javafx.controls,javafx.media,javafx.graphics testGoal6 songs.txt

goalset6_compilejava: testGoal6.java .env
	find *.java > temp.txt
	javac --module-path ${sdkPath}/lib --add-modules javafx.controls,javafx.media,javafx.graphics @temp.txt

goalset6_makejar: 
	jar cvfe testGoal6.jar testGoal6 *.class *.java

goalset6_runjar: 
	java --module-path ${sdkPath}/lib --add-modules javafx.controls,javafx.media,javafx.graphics -jar testGoal6.jar songs.txt
# 	find ${sdkPath}/lib/{javafx.controls.jar,javafx.media.jar,javafx.graphics.jar} -exec unzip -nq {} -d out \;
# 	cp ${sdkPath}/lib/{libprism*.so,libjavafx*.so,libglass*.co,libdecora_sse.so} out
# ------------------------

goalset4_runjava: goalset4_compilejava GoalSet4.class 
	java --module-path ${sdkPath}/lib --add-modules javafx.controls,javafx.media,javafx.graphics GoalSet4 songs.txt

goalset4_compilejava: GoalSet4.java .env
	find *.java > temp.txt
	javac --module-path ${sdkPath}/lib --add-modules javafx.controls,javafx.media,javafx.graphics @temp.txt

goalset4_makejar: 
	jar cvfe testGoal4.jar GoalSet4 *.class *.java

goalset4_runjar: 
	java --module-path ${sdkPath}/lib --add-modules javafx.controls,javafx.media,javafx.graphics -jar testGoal4.jar songs.txt
# 	find ${sdkPath}/lib/{javafx.controls.jar,javafx.media.jar,javafx.graphics.jar} -exec unzip -nq {} -d out \;
# 	cp ${sdkPath}/lib/{libprism*.so,libjavafx*.so,libglass*.co,libdecora_sse.so} out

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
	

