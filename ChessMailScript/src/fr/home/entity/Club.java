package fr.home.entity;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Thibaut
 */
public class Club {
    
    private String name;
    private String ref;
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

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Set<String> getPlayers() {
        return players;
    }

    public Set<String> getLeaders() {
        return leaders;
    }  
}
