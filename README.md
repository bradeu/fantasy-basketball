# Fantasy Basketball
## A Basketball Simulation

**What will the application do?** <br>
This application was made to simulate real games and predict the
outcomes of games with the players chosen by the users. This is done
for users who are curious how their favorite players will do when they are
teamed up together.

**Who will use it?** <br>
Avid fan of the every basketball league in the world will be able to use
this easily to get an accurate prediction on who will win on teams with the
given players.

**Why is this project of interest to you?** <br>
I am a big fan of the NBA, so I am often curious what will happen
if my favorite players teamed up with each other or go up against each
other.

<br>

*User Stories*
- As a user, I want to be able to create a new team and add it to a list of teams
- As a user, I want to be able to select a team and add a new player to the team
- As a user, I want to be able to list all the teams in the game
- As a user, I want to be able to get the predicted winners
- As a user, I want to be able to get each team's predicted scores
- As a user, I want to be able to put as many teams as possible
- As a user, I want to be able to save my teams and its players into a file (if I so choose)
- As a user, I want to be able to load my teams and its players from a file (if I so choose)

<br>

# Instructions for Grader

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by Adding team and Showing teams through the menu bar
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by Showing players through the menu bar
- You can locate my visual component by running GameAppGUI
- You can save the state of my application by pressing Save Game in the menu bar and then press the Save Game button
- You can reload the state of my application by pressing Load Game in the menu bar and then press the Load Game button

<br>

# Phase 4: Task 2

Run the game and Load Game.

Luka Doncic found
Luka Doncic added to the team
Team a added to the game
Lebron James found
Lebron James added to the team
Team b added to the game
Kawhi Leonard found
Kawhi Leonard added to the team
Team c added to the game
James Harden found
James Harden added to the team
Team d added to the game
James Harden found
James Harden added to the team
Team e added to the game
Mike Conley found
Mike Conley added to the team
Team f added to the game
Joel Embiid found
Joel Embiid added to the team
Team g added to the game
Kawhi Leonard found
Kawhi Leonard added to the team
Team shubh added to the game

<br>

**If you had more time to work on the project, what refactoring might you use to improve your design?**
I think one issue in this project that I forgot to anticipate from the beginning is the placement of the simulate game function. 
In my opinion it would be cleaner and better to change the function into a method inside the Game class. 
I feel that putting it in the UI doesn't make sense because it is essentially the function of the game and not just a visualization. 
So that is what I would do if I have more time.

Another area that is open to improvement is the Team class. Currently, the class has a method to read and find the player from the file,
I believe this method is not clean and would be better if I made another class that is in charge of finding players, reading from the file,
and creating the Player instances. To me, this would make the Team class more focused on the team related responsibilities.


