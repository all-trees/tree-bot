package nl.dvberkel.repository;

import nl.dvberkel.dyck.State;

public interface Repository {
    State load();
    void store(State nextState);
}
