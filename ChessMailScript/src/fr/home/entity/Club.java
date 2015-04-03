package fr.home.entity;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Thibaut
 */
public class Club {
    
    private String name;
    private final Set<String> players;
    private final Set<String> leaders;

    public Club() {
        this.players = new HashSet<>();
        this.leaders = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getPlayers() {
        return players;
    }

    public Set<String> getLeaders() {
        return leaders;
    }  
}
