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
#### Actions Module
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
    
## Design Details

## Example games
Movement Cards -- Give each player a set of cards numbered from 1 to 6. On each roll, a player uses one card and rolls one die. That player then moves the total of both. Each card must be used once before any can be used a second time. From Stephen Glenn.

https://www.ultraboardgames.com/monopoly/fortnite.php
https://www.ultraboardgames.com/monopoly/powers-of-cosmic-encounter.php
https://www.ultraboardgames.com/monopoly/junior-game-rules.php
https://www.ultraboardgames.com/monopoly/longest-game-ever-rules.php
## Design Considerations
