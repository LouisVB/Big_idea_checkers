package nl.fhict.s3.restserver.data;

import java.util.Collection;
import java.util.HashMap;
import nl.fhict.s3.restshared.Player;

public class PlayerStorage {

    private HashMap<String, Player> store;
    private static PlayerStorage instance;

    private PlayerStorage(HashMap<String, Player> store) {
        this.store = store;
    }

    public void addGreeting(Player player) {
        store.put(player.getUsername(), player);
    }

    public Player getGreeting(String key) {
        return store.get(key);
    }

    public void removeGreeting(String key) {
        store.remove(key);
    }

    public void EditPlayerData(Player player) {
        store.replace(player.getUsername(), player);
    }

    public Collection<Player> getAllPlayer() {
        return store.values();
    }

    public static PlayerStorage getInstance() {
        if (instance == null) {
            instance = new PlayerStorage(new HashMap<>());
        }
        return instance;
    }
}
