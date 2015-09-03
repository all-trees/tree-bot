package nl.dvberkel.repository.storage;

import nl.dvberkel.dyck.State;

public class InMemoryStorage implements Storage {
    private State lastState = null;

    @Override
    public void put(State state) {
        lastState = state;
    }

    @Override
    public State get() {
        return lastState;
    }
}
