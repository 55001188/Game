# Space Invaders Game

The object of the game is to kill all of the aliens before they reach you.

# Description
You as the player only get two lives to survive the alien invasion. Your goal is to kill all the aliens before they kill or reach you. They will repeatedly shoot out bullets so be careful not to get hit. If you do happen to lose a life, the consequence is that you are stalled out for a second before you can move again. The aliens, however will continue to advance, but will not shoot you before you get your next life. There are three levels of aliens, each worth a different amount of points. If you hit an alien, you score increases a certain amount depending on what level it is. You win if you are able to kill all the aliens.

# How to Play
Use RIGHT and LEFT arrow keys to move and SPACE to shoot.

# How does it work?
The mian classes involved in the project are the Player, Enemy, Bullet, and Frame classes. The majority of the code works with ArrayLists. All the enemies are stored in ArrayLists based on their level (2 list of easy-leveled aliens worth 10 pts, 1 list of medium-leveled aliens worth 20 pts, and 1 list of hard-leveled aliens worth 30 pts). These lists are traveresed multiple times to paint them on the screen, to test for bullet collision, and to test for invasion collision. The player lives are also stored in an ArrayList.

for gifs
or upload gif and import it into project and write " ![](name.gif)
example:
![Alt Text](url w/ .gif)
