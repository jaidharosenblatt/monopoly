Monopoly
====

This project implements a digital version of the classic board game, Monopoly as well as several variants.

Names:
Jaidha Rosenblatt,
Rodrigo Araujo,
Austin Odell

![alt text](https://i.imgur.com/ogoKNUL.png "Splash screen")
![alt text](https://i.imgur.com/DDPFqXH.png "Buying a property")

### Timeline

Start Date: 3/24/2020

Finish Date: 4/28/2020

Hours Spent: 200

### Primary Roles
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
* Austin - Frontend
    * Player Tab display
    * Rules tab
### Running the Program

Main class: ooga.Main.java, mark `/resources` as resources root


Data files needed: Everything in `/resources `
