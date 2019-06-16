package nl.fhict.s3.restserver.data;

import java.util.Collection;
import java.util.HashMap;
import nl.fhict.s3.restshared.User;

public class PlayerStorage {

    private HashMap<String, User> store;
    private static PlayerStorage instance;

    private PlayerStorage(HashMap<String, User> store) {
        this.store = store;
        setupPlayers();
    }

    public void addPlayer(User user) {
        store.put(user.getUsername(), user);
    }

    public User getPlayer(String key) {
        return store.get(key);
    }

    public void removeGreeting(String key) {
        store.remove(key);
    }

    public void EditPlayerData(User user) {
        store.replace(user.getUsername(), user);
    }

    public boolean userExist(User user) {
        User existedUser = getPlayer(user.getUsername());

        return existedUser.getUsername() == user.getUsername() && existedUser.getPassword() == user.getPassword();


    }

    public Collection<User> getAllPlayer() {
        return store.values();
    }

    public static PlayerStorage getInstance() {
        if (instance == null) {
            instance = new PlayerStorage(new HashMap<>());
        }
        return instance;
    }

    private void setupPlayers() {
        User Leon = new User("Leon", "Solid");
        User Frenk = new User("Frenk", "Newcar");
        User Louis = new User("Louis", "Gopher");

        addPlayer(Louis);
        addPlayer(Leon);
        addPlayer(Frenk);
    }

}
