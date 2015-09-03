package nl.dvberkel.repository.storage;

import com.google.gson.Gson;
import nl.dvberkel.dyck.State;

public class GsonStorage implements Storage {
    private final Gson gson;
    private String lastRepresentation;

    public GsonStorage(Gson gson) {
        this.gson = gson;
    }

    @Override
    public void put(State state) {
        lastRepresentation = gson.toJson(state);
    }

    @Override
    public State get() {
        if (lastRepresentation != null) {
            return gson.fromJson(lastRepresentation, State.class);
        }
        return null;
    }
}
