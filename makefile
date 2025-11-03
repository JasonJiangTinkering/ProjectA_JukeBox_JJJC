goalset2_makejar:
	jar cfe 

goalset2_compilejava: 
	javac paymentLauncher.java
	

goalset2_runjava:
	java paymentLauncher

run: goalset2_compilejava goalset2_runjava
	

