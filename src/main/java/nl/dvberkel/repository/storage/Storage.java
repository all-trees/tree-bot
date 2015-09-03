package nl.dvberkel.repository.storage;

import nl.dvberkel.dyck.State;

public interface Storage {
    void put(State state);
    State get();
}
