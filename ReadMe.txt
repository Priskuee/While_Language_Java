#Author Priyanshu Sharma
--TestCases.java and NewFeatureTestCases.java contain the main funtion
--AST.java has all the classes defination for AST for WHILE Language and ASTFeature.java contains all the class defination for new features
--NewFeature performs all the operations just opposite from the defination
	-Multiplication performs division
	-Subtraction performs Addition
	-Addition performs Subtraction
	-Negation returns same boolean
	-Equal returns negation of actual boolean
	-Less Than performs Greater than
	-And performs OR
	-Or performs And
	-Assign always assign 0 to the variable
	-Sequential does not perform first command
	-If command operated else's operation


Run The Code
javac TestCases.java
java TestCases

and 
javac NewFeatureTestCases.java
java NewFeatureTestCases