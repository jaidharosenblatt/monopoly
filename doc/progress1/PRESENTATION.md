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
    * Theme of board
    

## Distribution of Tasks
* Rodrigo: I plan to work mostly on the Back-End. I've already started working on implementing the player class and a game object hierarchy. The Tile class will be a superclass that is extended by Properties (which consist of Streets, Utilities, and RailRoads) and EventTiles (all other tiles). Next, I will focus on creating game logic to get a basic turn of the game working and a parser to be able to load in the original game of Monopoly.
* Kyle: Data and Backend?
* Jaidha: Front end: board view, turn actions, and user decision processing
* Austin: Front end: Tabs view, property display, settings (display and changing), Player information display
* Luke: Back end: Tiles, Houses, Cards, Elimination of player

## Extensions
* Adding Powerups
    * Players will be able to hold on to cards and use them at any point in the game. Each of these cards has a unique action that we will modify. These actions include controlling your roll, sabotaging other users, or to increase rent for your properties.
* Junior 
    * A simpler version of the game meant to be played by a younger audience. It is Monopoly without Houses, Trading, Mortgages, Free Parking, or Chance/Community Cards.
* Longest Game
    * A version of Monopoly that is 3 times larger and ensures that every property set a player lands on will be a monopoly from the beginning. However, a player can only win when they have bought all properties on the board. 
* Saving/Loading Games in Progress
    * User will be able to load in an XML file that represents a current state of game (player details, board, properties, etc) as well as save a current game as an XML file.
* Different UI Layouts
    * Ability to load a CSS file or XML file and change the theme of the user interface. This could include changing the language, a dark/light theme, changing the accent color, and custom layouts for windows.
* Different Board Themes
    * Players will be able to load different themes for Monopoly. Themes will change the text, colors, and images for the board, cards, and properties. 
*  High Scores
    *  At the end of the game, each player's total cash will be saved and added to a leaderboard of top 10 scores. This will involve saving and updating a XML file that holds the past high scores.

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

The design is attempting to follow a MVC style, with a focus on separation between the model and the view. The goal is to limit communication between the two to their two external APIs. Therefore the two ends will be closed from each other's point of view but open from an internal stance. Internally the front end will have a few basic layout components that will be extendable. These are the board, actions bar, tabs, and Game Display. The back end will be similar with an engine/model class that is flexible to handle turns and user actions. This will update the model, keeping track of the various players. It will communicate with the front end through its external API.

## Modules
* provide an overview of the project's modules: what is each responsible for and how does it depend on other modules
### Front End
* Board Module: This package will control how the board is displayed. This includes the properties, players, and the rest of the possible spaces. It is mainly just a display and will have little or no interactability from user input. 
* Actions Bar Module: Holds the buttons representing each possible pre-turn actions for a user to take. Will use reflection to dynamically add buttons for “roll dice”, “end turn”, “mortgage property”, and “trade”. These buttons will send an event to the Choices module or go through to controller class to send a call to the backend.
* Game Display Module: Handles user actions by using a buttons that implement the MethodClick functional interface. This is for during a turn when the model needs to ask the user for input (e.g. do they want to buy the property they landed on). The choices will be displayed to the front end and then the user will choose and this information will be sent back to the backend to continue processing the turn. 
* Tabs Module: Displays information about users and allows for non-player specific actions. Will work with the rest of the front end to update GUI theme/language. Will work with all of the game to update when a new game variant is selected. In general use it will get its information from PlayerInfos passed to the front-end through refreshDisplay. 


## API's
* describe two APIs in detail:
    * what service does it provide?
    * how does it provide for extension?
    * how does it support users (your team mates) to write readable, well design code?
* Front End External
    * This API provides a path for communication between to the view from the model. It provides methods such as displayRoll, movePlayer, and refreshDisplay that keep the display of the board in sync with the model. There is also the makeUserDecision method which allows for the model to request input from the user. 
    * It creates general use methods that can be used for various actions. For example refreshDisplay uses the PLayerInfo interface to communicate information about the players and can be extended to include additional information that is not yet thought of. Also the makeUserDecision method will be used for a variety of actions the backend may need to request from a player. 
    * It supports users by providing a steady and straightforwards way to interact with the front end. 

* Back End External
    * Similar to front end external, this API provides for a standard of requests to the model. This is used in two distinct ways. The first is to request information form the back end through methods like updateTiles and Update players. The other is to initiate game actions through methods like buyProperty, buyHouses, and trade.
    * This standard of communication allows for a reliable method of interaction between the model and the view. The implementation details are encapsulated and the return values are pretty generic. It allows for extension through the implementation of a concrete model module that will have functionality beyond just external communication and data management. 
    * It supports user to write well designed code because it provides a template for how to implement some well designed methods. 

## Use Cases

* Rolling dice
    * Player presses the roll dice button. This gets the handleRoll method from a map of keys to methods in the controller that has been passed down. We envoke the `handleRoll` which calls `rollDice(Player p)` method in the backend. Backend recieves this and creates a random number. This method gets passed back to the gui using the `List<Integer> getRoll() `method. The gui takes this each element from this list and displays a die with that roll.

* Displaying user information
    * This display will live under the TabDisplay section of the view. PlayerDisplay is a class that will implement the abstract tab class. The PlayerDisplay holds a list of Players and also has the ability to pass a playerInfo object to a particular player that allows a players assets to be displayed. The Player class itself will know all the info that needs to be displayed and such will be a visual node that shows all of this information to the user. Specifically it will have UI components for the player name, cash, and token. More in depth will be for their property holdings which will contain at least a name and a color. These properties will be sorted by colors so monopolies are grouped together.

## Alternative Design

* Ownership of properties
    * Option 1: Players hold properties
        * PlayerInfo holds list of their properties
        * Board "player" originally holds properties
        * Easy to get current properties of a user
            * Frontend can decide what properties to allow access to without accessing backend
        * Tradeable objects need to be added and removed from players
        * Difficult to get a list of all possible properties
            * Need to iterate through all players
        * Leads to complicated PlayerInfo class
    * Option 2: Properties know which player currently own them
        * Properties hold a userId variable that corrosponds to the player
        * userId is blank when a property is not held
        * Requires players having id
        * Similar to a relational database
        * Easy to get a list of all properties
        * Difficult to get current properties of a user
            * Frontend will need to iterate need to iterate through every property in order to maintain its properties
    * Option 3: Both options
        * Benefits of both options
        * Duplication in code might lead to more bugs and difficulty of use
    * We decided to go with **Option 1** since we thought it was important to allow the frontend to easily access their properties without having to perform logic (that should occur in the game engine). This will also make it easier to trade objects later in the process. We thought that it would not make sense to do both options since it would be difficult to maintain the code in multiple places. We also thought there might be issues with the playerId and property getting off-sync during runtime.
