# Java Games

Following the [Java Game Programming Wizard tutorial](https://www.youtube.com/watch?v=e9jRfgjV4FQ) to create my own top down games.

## [Maze Game](https://github.com/JessZuchowski/java_games/tree/master/src/main/java/java_games/mushroomMaze)

The first will be a game in which the user makes their way through a maze while dealing with enemies. Features will include:
1. Window with Canvas (buffered image reader/loader to render .png background).
2. Player object (mushroom buddy) controlled via keyboard and mouse.

   a. Keys to control movement (ASWD or arrows).
   
   b. Mouse clicks to control spore projectiles.
3. Enemy objects (slugs) that roam randomly.

   a. Enemies have hit points & take damage from player spores.
   
   b. At zero hp, slugs will curl up and stop roaming.
4. Camera to follow player through maze.
5. When player reaches end of maze a new level will start.

## Tools Used

JFrame

Canvas

[Maze Generator](http://www.mazegenerator.net/) for maze design inspiration.
