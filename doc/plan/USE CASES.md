# USE CASES

In another file, USE_CASES.md, write at least 8 use cases per person on your team that describe specific features you expect to complete.
## Austin
1. Player info display. This will be a display of all the current assets each player has and will also contain the player's name and an image that corresponds to their token on the board
This display will live under the TabDisplay section of the view. PlayerDisplay is a class that will implement the abstract tab class. The PlayerDisplay holds a list of Players and also has the ability to pass a playerInfo object to a particular player that allows a players assets to be displayed. The Player class itself will know all the info that needs to be displayed and such will be a visual node that shows all of this information to the user. Specifically it will have UI components for the player name, cash, and token. More in depth will be for their property holdings which will contain at least a name and a color. These properties will be sorted by colors so monopolies are grouped together. 
2. Buying a Property: 
this will require communication between the front and back end using the makeUserDecision method from the FrontEndExternal interface. Visually this will be in the Actions Bar and will create a Choices object. The choices object will display a String prompt in the Actions Bar area as well as create buttons that allow selection between the possible responses. When the user clicks one of these buttons the makeUserDecision will return the response to the back end for processing.
3. Paying rent (front end)


4. Setting Tab
5. Rules Tab
6. Properties Tab
7. Trade a property for money
8. PlayerInfo classes: the goal of this interface is to allow a standard for communication about a player between the model and the view


## Jaidha
1. Roll one die
Player presses the roll dice button. This gets the handleRoll method from a map of keys to methods in the controller that has been passed down. We envoke the handleRoll which calls rollDice(Player p) method in the backend. Backend recieves this and creates a random number. This method gets passed back to the gui using the `List<Integer> getRoll()` method. The gui takes this the first element from this list and displays a die with that roll.  
2. Roll multiple dice
Player presses the roll dice button. This gets the handleRoll method from a map of keys to methods in the controller that has been passed down. We envoke the handleRoll which calls rollDice(Player p) method in the backend. Backend recieves this and creates a random number. This method gets passed back to the gui using the `List<Integer> getRoll()` method. The gui takes this each element from this list and displays a die with that roll.  
3. Mortgage property
Player clicks "Mortgage Property" in ActionBar. Player is prompted to choose property to mortgage. This calls the backend method `void mortgageProp(Property p)`. This updates that property for that player in the backend. Backend then calls `void refreshPlayers(Map<Integer, PlayerInfo> currentPlayers)` to update the property to mortgaged.
4. Trade a card for property
Player clicks "Trade" in ActionBar. This prompts the player to select which assets to trade and the view returns the items to trade that the player chose. We execute `void trade(Player p, TradeObject give, TradeObject get)` for every item that will be traded. This updates that property in the backend. Backend then calls `void refreshPlayers(Map<Integer, PlayerInfo> currentPlayers)` to update each player's assets accordingly.
5. New turn
Player is set as active in the backend. Player is highlighted in the gui and given the choice of actions in ActionBar.
6. Land on community chest and collect money from card
Player rolls dice in frontend and recieves a roll and sees that they landed on community chest. Backend randomly selects a card and determines that player won $200. PlayerInfo is updated +200. Gui is passed next String "You won $200" to display through `void displayText(String text)`. Gui is updated with `void refreshPlayers(Map<Integer, PlayerInfo> currentPlayers)` to update player's money.
7. Land on community chest and collect "get out of jail free card"
Player rolls dice in frontend and recieves a roll and sees that they landed on community chest. Backend randomly selects a card and determines that player will recieve a get out of jail free card. This is added to the player's cards in the backend. Gui is updated with `void refreshPlayers(Map<Integer, PlayerInfo> currentPlayers)` to update that player's cards.
8. Buying a house
Player clicks "Buy houses" in ActionBar. This prompts the user to choose what monopoly to buy houses for and how many houses to buy for each property. This is passed to the backend for each property using `void buyHouses(Property P, int amount)`. The backend adds houses each property and updates gui using `void refreshPlayers(Map<Integer, PlayerInfo> currentPlayers)`.