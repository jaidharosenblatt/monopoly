# DESIGN_PLAN

## Introduction
We are building variants of the classic game of Monopoly. The primary design goal of this project is to organize our code into external APIs for the game engine/backend and player/frontend that are fed data through a controller/data module. We wanted to use this seperation and abstraction in order to have flexibility without each part needing to know specific implementations of each other's methods. Our frontend is only for visualizing the board and information about the players. The layout is closed off from the backend but it is open for modification of the player and board. The frontend also needs to be able to prompt users with possible decisions (rolling dice, buying properties, etc). The backend is responsible for handling any actions requested by the player. This means that handling for player movement, buying properties, and trading projects are all closed for direct modification. It is open to recieving actions given by the player and for reading in a new game layout from the data module. The data module is used to communicate between the player and engine and load/save versions of the game. It is closed for handling the reading of data but open to modification from the engine and player.

## Overview

### Backend
* Roll Dice
    * returns the roll result to the player
    * `int rollDice(Player p)`
    * returns the roll for player p
* Updated Tiles
    * `List<TileInfo> updateTiles()`
    * returns an immutable state that represents the state of all tiles
* Updated Players 
    *`List<PlayerInfo> updatePlaters()`
    * returns an immutable state that represents the state of all players
* Buy Property
    * `void buyProperty(Property P)`
* Mortgage Property
    * `void mortgageProp(Property P)`
    * has to check if player owns property
* Buy Houses
    * `void buyHouses(Property P, int amount)`
    * has to check if you own the full set of properties and if you can afford it
* Trade Object
    * tradeable object is a property, card, or money
    * `void trade(Player p, TradeObject give, TradeObject get)`
* Pay/roll/free card to get out of jail

        
### Frontend
* ViewController
    * Holds the Board, Actions Bar, Game Display, and Tabs
    * Implements the external API interface in order to control the entire view
#### Board Module
This package will control how the board is displayed. This includes the properties, players, and the rest of the possible spaces
* Board
    * Has `void trade(Player p, TradeObject give, TradeObject get)` method
    * Indirectly called using `refreshPlayers(Map<Integer,PlayerInfo> currentPlayers)` method for updating the board
    * Has `void changeTheme (String pathToThemePropertyFile)` method for changing the theme of the board
    * Holds ImageView objects that represents each player
    * Holds all of the spaces
    * Allows modification to individual spaces
    * Has resource bundle for mapping prompts and images to spaces
* Space
    * Displays image
    * Holds prompt
* Property
    * Inherits the Space class
    * Display price
    * Display image asociated with type
* Card
    * Has action from CardActions class
    * Displays card prompt and image

#### Actions Bar Module
Holds the buttons representing each possible pre-turn actions for a user to take. Will use reflection to dynamically add buttons for "roll dice", "end turn", "mortgage property", and "trade". These buttons will send an event to the Choices module or go through to controller class to send a call to the backend.
#### Game Display Module
Handles user actions by using a buttons that implement the MethodClick functional interface. 
* Chance
    * Displays roll value
* Dice
    * Inherits chance
    * Has `void rollDice(List<Integer> diceResults)` method for display dice
* Choices
    * Is passed a list of methods from Controller class
    * Is an EventListener for ActionsBar
    * Get prompt from a `int makeUserDecision(String prompt, List<String> possibleResponses)` API call
    * Get possible responses and use ButtonFactory to determine the appropriate methods to call in controller 
* ButtonFactory
    * Creates button
    * Uses MethodClick interface
#### Tabs Module
Displays information about users and allows for non-player specific actions.
* Tabs
    * Uses reflection to add tabs
* PlayersDisplay
    * Collection of Player objects
* Player
    * Display all of a player's properties and cards
    * Display current cash on hand
    * Display user color, name, and avatar
* Settings tab
    * Control GUI theme
    * Control language
    * Set Monopoly theme
    * Load Monopoly rules using data
* Rules
    * Display formatted text explaining the rules of the game
    
### Controller / Shared
* PlayerInfo
    * Standard for communicating information about a player
    * Contains data about all assets held
    * Board position double check

![UML Diagram for front end](https://i.imgur.com/xv0jv6M.png "Front End UML")
![UML Diagram for back end]()

## Design Details

### PlayerInfo 
The purpose of this interface is to create a standard way for communicating between the front and back ends about the status of all of the players. Data will be compiled from the back end to create one of these at the end of every turn. From the front end this will be used to update the player view under the tabs pane to display what assets they hold. Also it will be used to verify that the tokens on the board are in the right position. 
methods: all used to extract data from an encapsulated PlayerInfo class
*  `public List<Property> getPropertiesUnmodifiable();`
this method returns a list of all of the properties a player owns in a way that is not modifiable. 
*  `public List<Card> getHeldCards();`
this method is similar to the previous except it returns non-property holdings (cards) of a player such as a get out of jail free card
*  `public int getCashBalance();`
    Used to update the display under the players tab that shows their current amount of money
*   `public int getPositionOnBoard();`
though the animation of player movement should have moved the player's token to the right position on the board this will check that to ensure the location is in sync with the model

#### Board (Frontend)
The purpose of this module is simply visualizing all of the properties, player positions, on a board. We are using a Board interface in order to make sure that our board have flexibility with its layout, number of spaces, and way of visualizing players. We include `void movePlayer(int playerId, int numSpaces)` and `void int getSpaceActive()` in order to make sure that regardless of the type of board, we can move players and update the state of spaces in the GUI. It will only be modifyiable through the ViewController class.

#### ActionsBar (Frontend) 
This module will simply control the actions that players can take throughout a turn. We chose to not use an interface for this module, since we will use reflection and data files in order to choose which buttons to display depending on the version of the game. For example, the classic version will have "roll dice", "end turn", "mortgage property" while other versions might have a "active card" option. However, the actions will all be different. We will have a seperate PlayerActions Class that has the possible methods that players can call. These methods will be passed down from the Controller in order to directly call the game engine. For each of these buttons, we will use a functional interface ButtonClick to ensure that they all have the same actions when clicked.

#### Game Display Module
The game display module will be a flexible class that is given options from the backend. Through the Decision interface, it is given a prompt and choices to present the user. Each of these choices will be an option in a radio select. The user will choose an option and return it using `void setChoice(int choice)`.

It is also responsible for displaying a roll. We are using a Chance interface in order make our design flexible for types of different ways of displaying rolls (multiple dice or just a number to display). This interface has a single method, `List<Integer> displayRoll()` for displaying all of the randomly generated numbers from the model.

#### Tabs Module


## Example games
Movement Cards -- Give each player a set of cards numbered from 1 to 6. On each roll, a player uses one card and rolls one die. That player then moves the total of both. Each card must be used once before any can be used a second time. From Stephen Glenn.

### Powerups
[Modified from](https://www.ultraboardgames.com/monopoly/powers-of-cosmic-encounter.php)
* In this modification, players can hold on to cards and use them at any point in the game. Each of these cards has a unique action that we will modify form powers of cosmic encounter. These actions include controlling your roll, sabotaging other users, or to increase rent for your properties.
* Our PlayerInfo interface allows players to hold cards (which all have an activate method on a certain player). We can set these actions in the backend by modifying another's player info values or roll mechanism by creating different implementations of "powered up" players. For simplicity, it would make sense to only be able to hold one power up at once to not have to deal with a near infinite combinations of concrete classes for combined power ups.


### Junior
[Modified from](https://www.ultraboardgames.com/monopoly/junior-game-rules.php
)

### Longest Game
[Modified from](https://www.ultraboardgames.com/monopoly/longest-game-ever-rules.php)
Key Differences:
* Extra Long Board: this is supported by the use of a generic board class that can handle any number of tiles. On the front end this means that the display size of the board will be adjusted based on its length. It should be safe to assume that all boards can be represented by a square so just the size of the square and of the individual tiles will be calculated dynamically. 
* Three versions of every property: To fill all the extra spaces each property is repeated three times on the board. This will require that whenever a property is bought all three versions get added to the players list of properties. This is important because while buying one gives you all three, you can arrange houses on them separately. 
* Winning the game: In this version a winner is only crowned when they control all of the properties. This means that players are still in when they go bankrupt,though they don't pay anything and can only earn money(e.g. through passing GO). A specific rules class implementation on the back end will be able to evaluate the rules for bankrupty and winning the game
## Design Considerations
