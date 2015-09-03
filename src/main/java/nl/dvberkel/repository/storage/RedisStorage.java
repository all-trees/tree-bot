package nl.dvberkel.repository.storage;

import com.google.gson.Gson;
import nl.dvberkel.dyck.State;
import redis.clients.jedis.Jedis;

public class RedisStorage implements Storage {
    private static final String LAST_STATE = "last.dyck.word";
    private final Jedis jedis;
    private final Gson gson;

    public RedisStorage(Jedis jedis, Gson gson) {
        this.jedis = jedis;
        this.gson = gson;
    }

    @Override
    public void put(State state) {
        String json = gson.toJson(state);
        jedis.set(LAST_STATE, json);

    }

    @Override
    public State get() {
        String json = jedis.get(LAST_STATE);
        if (json != null) {
            return gson.fromJson(json, State.class);
        }
        return new State("", 0);
    }
}
