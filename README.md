# PatternsProject

# War Card Game

A Java desktop version of the card game War, built for a Vanier College software patterns project. I expanded my earlier War game into an application with a graphical interface, organized game logic, and database-backed records.

## Features

- Play a War game between two players
- Track rounds, wins, ties, and game statistics
- View and manage saved players and games
- Display the game through a JavaFX interface

## Technologies and design

- **Java** for the application and game logic
- **JavaFX and CSS** for the interface
- **MySQL and JDBC** for saved records
- **MVC structure** to separate models, views, and controllers
- **Factory classes** to create game and deck objects
- **Maven** for the project build

## Project structure

The application source is in [`WarCardGameMVCApplication`](WarCardGameMVCApplication). The database setup script is [`war_game_db.sql`](war_game_db.sql).

## My contribution

We built the game by extending an earlier project of mine. For this version, I developed the game logic, organized the application into MVC components, connected it to MySQL, and worked on the graphical interface.

## Running the project

1. Set up a local MySQL database using `war_game_db.sql`.
2. Open `WarCardGameMVCApplication` as a Maven project.
3. Configure the local database connection for your environment.
4. Run the `com.wargame.Main` class with JavaFX available.

This was a school project and is intended as a demonstration of application structure and Java development.
