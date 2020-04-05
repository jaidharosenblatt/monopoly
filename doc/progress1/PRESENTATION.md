# Presentation Design Plan

## Genre or Game
* The game we are building is Monopoly.
*  Commonalities: All the monopoly versions we will implement have the following features:
    *  Turn based movement
    *  Ownership/purchasing of property
    *  Paying rent
    *  Collecting money

* Differences: Some key differences between the versions:
    * length of the board
    * Winner determination
    * Trading between players
    * Rolls
    * Chance/Community Cards and their actions
    

## Distribution of Tasks
* Rodrigo: I plan to work mostly on the Back-End. I've already started working on implementing the player class and a game object hierarchy. The Tile class will be a superclass that is extended by Properties (which consist of Streets, Utilities, and RailRoads) and EventTiles (all other tiles). Next, I will focus on creating game logic to get a basic turn of the game working and a parser to be able to load in the original game of Monopoly.
* Kyle: Data and Backend?
* Jaidha: Front end: board view, turn actions, and user decision processing
* Austin: Front end: Tabs view, property display, settings (display and changing), Player information display
* Luke: Back end?

## Extensions
* Adding Powerups
    * Players will be able to hold on to cards and use them at any point in the game. Each of these cards has a unique action that we will modify. These actions include controlling your roll, sabotaging other users, or to increase rent for your properties.
* Junior 
    * A simpler version of the game meant to be played by a younger audience. It is Monopoly without Houses, Trading, Mortgages, Free Parking, or Chance/Community Cards.
* Longest Game
    * A version of Monopoly that is 3 times larger and ensures that every property set a player lands on will be a monopoly from the beginning. However, a player can only win when they have bought all properties on the board. 

* Saving/Loading Games in Progress
* Different UI Layouts
* Different UI Themes
*  High Scores

* Dream (unlikely to be implemented) extensions:
    * Artifical Player
    * Networked Players
## Sprints
* Sprint 1
    * Basic implementation of the classic version of Monopoly. Players can see the board, roll dice and move, buy properties and houses, pay rent, and see the display info for other players. 
* Sprint 2
    * Complete implementation of classic version of Monopoly. Adding adjustments to settings, advanced actions, cards, and special events. Players can trade cards/money/property, land on go, go to jail, buy houses and hotels, mortgage properties, and manually end their turn.
* Sprint 3
    * Complete monopoly implementation and add in the extensions. These include the different game types and their associated rulesets as well as saving/loading, UI themes and layouts, high score tracker, and then the dream extensions. 

## Demo
* demo the User Interface Wireframe to show how the user will interact with the program
Images in doc/wireframe

## Design and Architecture Goals
* describe the design and architecture goals: what is expected to be flexible/open and what is fixed/closed

## Modules
* provide an overview of the project's modules: what is each responsible for and how does it depend on other modules

## API's
* describe two APIs in detail:
    * what service does it provide?
    * how does it provide for extension?
    * how does it support users (your team mates) to write readable, well design code?

## Use Cases
* describe two use cases in detail that show off how to use one or more of the APIs described previously

## Alternative Design
* describe one alternative design considered and what trade-offs led to it not being chosen
