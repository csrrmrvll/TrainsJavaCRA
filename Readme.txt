Readme
______

TrainsJavaCRA

1. Install instructions:

Download and install (add to PATH) the latest Maven version: https://maven.apache.org/download.cgi
Uncompress TrainsJavaCRA.zip and run: PROMPT>mvn install in the directory, tests are automatically triggered.
Alternatively you can open it as an Eclipse project, build it (ctrl+B) and run GraphTest as JUnit.
Output can be checked in both procedures, tests don't run in order, but numbers are provided.

2. Design assumptions:

The problem to solve consists in getting information about routes in a graph.
I have used Depth-First Search to compute the routes, establishing a maximum number of stops in a route
arbitrarily to 10 (needed to stop the search). At first I thought the solution needed
Dijkstra's Shortest Path algorithm, but I sorted roads on their distances as a preprocessing stage,
and maybe even this step is not needed, just a minor optimization.
The code is not optimal in the sense that a road-labelling procedure will avoid some
redundant computations currently performed by the solution, but lack of time and the fact that the input
is small and no real performance penalty is involved have prevented me from attempting a further approach.

3. Project history:

I have used Git as SCM, all history is included and can be checked (.git folder); I have tried to log meaningful messages.
