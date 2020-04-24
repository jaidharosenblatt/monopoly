package ooga.BackEnd.GameLogic;

import ooga.BackEnd.GameObjects.Player;
import ooga.BackEnd.GameObjects.Tiles.Tile;
import ooga.api.objects.PlayerInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author rodrigoaraujo creates players and randomizes their orders
 */

public class CreatePlayers {

    private List<Player> activePlayers;
    private Map<Integer, Player> playerTabMap;

    /**
     * Handles creating players and randomizing the order of them
     *
     * @param playerInfo map of player name and player color in strings
     * @param allTiles list of all tiles on the board
     */

    public CreatePlayers(Map<String, String> playerInfo, List<Tile> allTiles) {
        this.activePlayers = new ArrayList<>();
        Player[] players = new Player[playerInfo.size()];
        int i =0;
        for (String playerName : playerInfo.keySet()){
            Player player = new Player(playerName, allTiles);
            String color = playerInfo.get(playerName);
            player.setColor(color);
            players[i] = player;
            i++;
        }
        this.playerTabMap = new HashMap<>();
        Player[] temp = rollForOrder(players);
        for (Player p : temp) {
            this.activePlayers.add(p);
            this.playerTabMap.put(activePlayers.indexOf(p), p);
        }
    }

    private Player[] rollForOrder(Player[] list) {
        Player[] order = new Player[list.length];
        int counter = 0;
        ArrayList<Integer> chosen = new ArrayList<>();
        while (chosen.size() < list.length) {
            int probality = (int) (Math.random() * list.length);
            if (!chosen.contains(probality)) {
                chosen.add(probality);
                order[counter] = list[probality];
                counter++;
            }
        }
        return order;
    }

    /**
     * Gets activePlayers
     *
     * @return List of Players
     */

    public List<Player> getActivePlayers() {return activePlayers;}

    /**
     * Gets playerTabMap
     *
     * @return Map
     */

    public Map<Integer, Player> getPlayerTabMap() {return playerTabMap;}
}
