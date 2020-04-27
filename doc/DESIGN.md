# DESIGN

## Names
Rodrigo Araujo
Jaidha Rosenblatt
Austin Odell
Luke Peterson
Kyle Brisky

## Roles
* Rodrigo - Backend
    * XMLParser and XML files
    * Backend player action files (House, Mortgage, Trade)
    * Designed LoadGame to handle logic for each turn
    * Defined objects for use in game
        * Player, Cards, Tiles
    * Designed class hierarchy for Tiles
        * Tiles -> Property and EventTiles
                * Property -> Railroad, Utility, Street
                * EventTiles -> Go, Jail, GoToJail, CardTile, FreeParking, Tax
    * Designed example games (Rainbow, World, Space, and Classic)
        * Rainbow: Every property is its own monopoly (houses can be bought immediately)
    * Worked with Jaidha on implementing backend/frontend
* Jaidha - Frontend
    * Board visualizations
    * Decisions windows
    * Player actions
    * Designed Decision, PlayerInfo, Board, and FrontEndExternal interfaces
    * Worked with Rodrigo on integrating frontend/backend
## Design Goals
### Frontend
- Wanted to minimize frontend's control of objects
- Focused on making it easy to add new ways prompt users for actions
- Easy to add actions
- Easy to add items to players
- Difficult to change board size
### Backend
- Focused on making game flexible to intake user's choices
    - At any point in a turn, user has freedom to take whatever action (as long as they can afford it)
- Made creating new games easy by defining games as XML files
- Rules separated into different classes to allow for easy changes
    - Bankruptcy is handled outside of LoadGame and could be changed to accomodate a different version of monopoly
- Game events handled by tiles themselves in action methods
    - Allows for easy implementation of new types of tiles
## High level design

### Frontend
- Broke up compontents into packages
    - actions
    - board
    - splash
    - tabs
- Each package has a class that is responsible for handling methods from the API (ie ClassicBoard moves players and displays rolls)
- The View class implements the frontend API and controls all classes in the frontend (aside from the splash screen)
- The splash screen sets the info for each player and launches the backend with a specific type of game
### Backend
- Two components: Game Logic and Game Objects
    - Game Logic: handled the backend side of player actions, decisions, turn logic, and end game logic
    - Game Objects: defined what could be possible on the board
- All tiles have the capability to handle what happens when a player lands on them
    - Allows for the addition of new tiles in the Event or Property category and could be implemented quickly into a new game mode
## Asumptions
### Frontend
- Chance/player rolls can always be represented with a list of ints
- Board will always be 40 tiles and square
- There is a max of 6 players and min of 1 player
- Specific min window size in order to display full board
### Backend

## Adding new features
### Frontend
- Adding another player action
    - Add method to frontend API that correspond to action
    - Create backend method to call
    - Add method to View that calls backend method
    - Add button to TurnActionButtons class and call method in View
- Adding new board design
    - Implement Board interface and methods
    - Use TileFactory to add elements in desired order
    - Switch View to have a parameter for setting board type
    - View creates new Board instead of classic Board
### Backend
- Adding another player action
    - Create method in LoadGame and implement logic in new class in PlayerActions
    - Add appropriate frontend implementations
- Changing how Game ends
    - Define new logic in a class (similar to Bankruptcy)
    - Create method to toggle between game endings based on XML urls
    - Insert method inplace of Bankruptcy in takeTurn
- Changing values of boardgame tiles
    - tile color, name, and game related values can be changed through XML