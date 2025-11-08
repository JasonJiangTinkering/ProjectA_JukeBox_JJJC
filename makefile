goalset2_makejar:
	jar cvfe testGoal2.jar paymentLauncher *.class *.java

goalset2_runjar: testGoal2.jar
	java -jar testGoal2.jar

goalset2_jar:  goalset2_makejar goalset2_runjar
goalset2_compilejava:  paymentLauncher.java

	find --name *.java > temp.txt
	javac @temp.txt
	javac paymentLauncher.java 
	

goalset2_runjava: paymentLauncher.class
	java paymentLauncher

goalset2_run: goalset2_compilejava goalset2_runjava
	

