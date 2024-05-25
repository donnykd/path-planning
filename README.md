# Path Planning in a Game Environment
An unlisted youtube video describing the project: https://youtu.be/1BO0VXOI-cM

## This is Galid Mahamed's Work on AI Planning
This repository contains PDDL files such as the domain which is called "explorer.pddl" and 7 problem files each in their own folder. The problem file folders also contain the expected test results for the problem files which are used to test them.

An A* algorithm was also created and is inside the maven project folder called heuristic. This folder holds the classes and tests created when implementing an A* algorithm with a domain specific heuristic. The main classes can be found in the subdirectory *..heuristic\src\main\java\com\gmahamed\search* while the tests can be found in *..heuristic\src\test\java\com\gmahamed\search*

### Pre-requisites for the PDDL files:
1) Jinja2 installed. This can be done by running python -m install jinja2 on terminal.
2) FastDownward installed, follow the example given below.

### Instructions on how to run the PDDL problem files:
There are two methods I used to run the PDDL files, either through FastDownward which can be installed by following the github build.md file https://github.com/aibasel/downward/blob/main/BUILD.md and explained:
1) Navigate to the FastDownward directory wherever it is.
2) Build the planner by running: ./build.py.
3) To run the planners use any of the provided ones from https://www.fast-downward.org/IpcPlanners. To use them you write ./fast-downward.py path/to/your/domain.pddl path/to/your/problem.pddl .followed by any of the planners, simply not having a planner specified also works.

The other method is to run it with planning engines provided by the IDE Visual Studio Code's PDDL extension such as paas-uom.org:5001. This can be done with the following steps:
1) Locate the problem file to run in your directory and open it in visual studio code.
2) Either right click on the file and press "PDDL: Run the planner" of open the command pallete by pressing 'CTRL+SHIFT+P' and typing "PDDL: Run the planner".
3) Select the matching domain file, in this case simply 'explorer.pddl'.
4) Select the planner of choice.

### Pre-requisites for the A* Algorithm:
1) Have maven running on the system as it is a maven project.

### Instructions on how to run the algorithm:
1) Locate to the maven directory, which might simply be cd ./heuristic/
2) To run the entire program with its test, enter the following command in the command prompt 'mvn clean install'
3) To run the main method in the A* class enter the following command in the command prompt 'mvn clean javafx:run'


