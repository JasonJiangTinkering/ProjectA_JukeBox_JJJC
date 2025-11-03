goalset2_makejar:
	jar cvfe testGoal2.jar paymentLauncher *.class *.java

goalset2_runjar:
	java -jar testGoal2.jar

goalset2_compilejava: 
	javac paymentLauncher.java
	

goalset2_runjava:
	java paymentLauncher

run: goalset2_compilejava goalset2_runjava
	

