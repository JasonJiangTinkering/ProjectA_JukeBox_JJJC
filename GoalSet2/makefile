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
	

