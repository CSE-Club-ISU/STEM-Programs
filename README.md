# STEM-Programs
This is a project to help teach students in elementary school the basics of problem-solving.

### Relevance to STEM
Computer science is about solving problems, some important topics are optimization and counting possibilities. 
One of the most common ways to solve complex problems is through reduction.
Reduction is when we take in input of the problem and rewrite it so that we can plug it into another already solved problem. 
We commonly reduce optimization and counting problems to a problem to a form of special graph, and a mazeGame can be thought of as a restricted graph. 
In a way, teaching kids about mazes is an introduction to solving some of these complex problems.
A common problem is to give the instructions on how to get from point A to point B in a mazeGame. 
To solve this we need to create an algorithm to search all possibilities in a mazeGame. 
When creating an algorithm, the logic has to be determined before trying to solve the mazeGame. 
So our challenge to the kids is to give us a list of instructions to move a dot through a mazeGame.

<img src="https://github.com/user-attachments/assets/ec7b4873-07bf-4fa6-b492-2c47effaee7a" width="40%"/> <img src="https://github.com/user-attachments/assets/4c9ce856-3117-4116-afd8-8ed7204ee87d" width="40%"/> 
<img src="https://github.com/user-attachments/assets/56377cee-94c8-4456-8634-fe44885826f9" width="40%"/> <img src="https://github.com/user-attachments/assets/1488af03-a2a8-4735-be03-d8a7c354ea69" width="40%"/>

### How can I contribute?

You are welcome to contribute in any way you see fit.
Part of this project is to provide a learning experience for those interested in programming.


### Creating your own program
1. Create a new directory under src/main/java/programs with your program name.
2. Create a new class in your package with the same name as your package name.
3. Create a `public static Program programFactory()` method in that class.
You can customize the name and description of the program in your Program object.
See `ProgramTemplate.java` for a good example.
4. Create a new class that will be your Program's main panel and have the `startProgram()` method in the program 
object create a new instance of it.
5. Now you should be able to run the program and see your program on the start menu.

### Todo:
* Create better mazeGame algorithms
* Improve the UI
* Create other algorithm games:
  * Higher or lower?
  * Simple Programming games
  * Puzzle games

Running the program with a .jar File:
* Run `./gradlew jar`
* The jar should be in the `build/libs` directory
* Run `java -jar build/libs/<Name>.jar` or right click and run in intellij
