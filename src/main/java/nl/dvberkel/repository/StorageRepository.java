package nl.dvberkel.repository;

import nl.dvberkel.dyck.State;
import nl.dvberkel.repository.storage.Storage;
import nl.dvberkel.repository.Repository;

public class StorageRepository implements Repository {
    private Storage storage;

    public StorageRepository(Storage storage) {
        this.storage = storage;
    }

    @Override
    public State load() {
        State state = storage.get();
        return state != null ? state: new State("", 0);
    }

    @Override
    public void store(State nextState) {
        storage.put(nextState);
    }
}
