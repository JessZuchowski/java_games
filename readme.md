# Java Games

Following the [Java Game Programming Wizard tutorial](https://www.youtube.com/watch?v=e9jRfgjV4FQ) to create my own top down games.

## [Maze Game](https://github.com/JessZuchowski/java_games/tree/master/src/main/java/java_games/mushroomMaze)

The first will be a game in which the user, as a little mushroom buddy, makes their way through a maze while dealing with hungry slug enemies. The player can shoot spores at enemies, which depletes their own nutrient stores and damages enemies. Nutrients can be collected as found to replenish either spores or health. Features will include:
1. Window with Canvas (buffered image reader/loader to render .png background).
2. Player object (mushroom buddy) controlled via keyboard and mouse.

   a. Keys to control movement (ASWD or arrows).
   
   b. Mouse clicks to control spore projectiles.
   
   c. Spore dispersal depletes nutrient stores.
   
   d. Collision with enemies decreases hit points. 
   
3. Enemy objects (slugs) that roam randomly.

   a. Enemies have hit points & take damage from player spores.
   
   b. At zero hp, slugs turn into nutrients for player to collect.
4. Camera to follow player through maze.
5. When player reaches end of maze a new level will start.

## Tools Used

JFrame

Canvas

[Maze Generator](http://www.mazegenerator.net/) for maze design inspiration.
