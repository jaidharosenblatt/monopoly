# Demo Project Team 19

## Functionality
### Program Demo
[Demo](https://www.youtube.com/watch?v=_BBgyPY1lYc&feature=emb_title)

### Tests
Unfortunately, we were unable to integrate tests and decided to focus more on our design and functionality.

## Design
### Revisting Goals
#### Frontend
- Generally design was very flexible
    - Decisions
    - Player display
    - Rules
    - Splash screen
    - Properties and tiles
- Some aspects were specific to classic Monopoly
    - `Board` has very specific layout to classic Monopoly
        - Would need to copy a lot of code in order to create 
    - Could use `Board` interface which just has `void movePlayer(PlayerInfo player, int newPosition)` and `void displayRoll(List<Integer> rolls)`
    - Die is only applicable to dice rolls
        - Could very easily instead just display a number
#### Backend @Rodrigo
### APIs Description
#### PlayerInfo
```
  List<PropertyView> getPropertiesUnmodifiable();
  Integer getCashBalance();
  Integer getPositionOnBoard();
  String getPlayerColor();
  String getName();
```
- Communication between frontend and backend for displaying information about players without modification
- All players will have these aspects. If we needed additional player information, we could extend this class.
- Abstracts implementation details from the frontend (so that it does not have direct control of players)
- Creates one way to handle players
- Changes over Sprints
    - Removed ` List<Card> getHeldCardsUnmodifiable()` since we ran out of time to let players hold cards outside of get out of jail
    - Added `String getPlayerColor()` and `String getName()` since we wanted to augment the frontend to display more information about players
- Showcase `PlayersTab`

#### Decision
```
  String getPrompt();
  List<String> getOptions();
  void setChoice(String choice);
  String getChoice();
```
- Interfaces for displaying decisions that the user will make in thez frontend
- Allows for multiple choices, single choices, and various types of options
- Abstracts all decisions made by user to a prompt, choices, and the decision made
- Forced us to write flexible code that will always support multiple types of decisions  
- Change over sprints
    - Added `String getChoice()` method to allow backend to actually get the choice of the user
- Showcase `Decisions` and `DecisionView`in frontend
- @Rodrigo figure out what classes to show in backend


## Team
#### Differences From Plan
- Plan
    - Sprint 1: implement basic Monopoly with no actions or trading
    - Sprint 2: implement full version of classic Monopoly
    - Complete: add extensions
- Reality
    - Sprint 1: implemented text based Monopoly in backend and board in frontend
    - Sprint 2: connected frontend and backend logic; added trading and other actions
    - Complete: creates tabs (rules, player display), created splash screen, improved design by using interfaces in backend
    -
#### Significant events
- Integrating frontend and backend
    - Frontend was running based on buttons
    - Backend was entirely text based
    - Jaidha and Rodrigo sat together in Zoom call for 3 hours
        - Explaining how each other's code worked
        - Relating our design back to APIs
        - Figuring out how to reduce need of a controller class
- Coordinating how to handle decisions
    - Frontend was doing decisions in the tab pane
    - Had decision interface unclear how to implement in frontend
    - Frontend had to deal with asynchronous user actions
    - Jaidha was planning on calling methods using a controller
    - Rodrigo was planning on recieving return values from frontend method calls
    - Rodrigo suggested having decisions open a new stage
    - Jaidha implemented and was able to pass the user decision back to backend through the `Decision` interface


### Team improvements
- Communication was difficult since we were all working remote
    - Difficulty with living at home
        - Austin computer breaking
        - Other commitments
- We actively tried to work on communicating over chat and video chat
- No issues with code pushing
- We could have *improved* by having more meetings, such as daily standups to get updates on each other's progress

### Revisting Team Contract
- Still useful
    - Meetings twice a week
    - GroupMe communication
    - Consensus based decision making policy 
    - Each member is expected to continue to make weekly progress and never hinder teammates ability to move forward with their portion of the project
    - During each deliverable presentation, each team member is responsible for explaining and presenting code that they wrote
- To add
    - Difficulty understanding other people's code
    - Add additional scheduled meetings between smaller teams (frontend team or backend team)
