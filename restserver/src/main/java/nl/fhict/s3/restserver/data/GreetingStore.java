package nl.fhict.s3.restserver.data;

import java.util.Collection;
import java.util.HashMap;
import nl.fhict.s3.restshared.Greeting;

public class GreetingStore {

    private HashMap<String, Greeting> store;
    private static GreetingStore instance;

    private GreetingStore(HashMap<String, Greeting> store) {
        this.store = store;
    }

    public void addGreeting(Greeting greeting) {
        store.put(greeting.getName(), greeting);
    }

    public Greeting getGreeting(String key) {
        return store.get(key);
    }

    public void removeGreeting(String key) {
        store.remove(key);
    }

    public void replaceGreeting(Greeting greeting) {
        store.replace(greeting.getName(), greeting);
    }

    public Collection<Greeting> getAll() {
        return store.values();
    }

    public static GreetingStore getInstance() {
        if (instance == null) {
            instance = new GreetingStore(new HashMap<>());
        }
        return instance;
    }
}
