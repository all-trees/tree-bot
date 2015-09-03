package nl.dvberkel.repository;

import com.google.gson.Gson;
import nl.dvberkel.dyck.State;
import nl.dvberkel.repository.storage.GsonStorage;
import nl.dvberkel.repository.storage.InMemoryStorage;
import nl.dvberkel.repository.storage.RedisStorage;
import nl.dvberkel.repository.storage.Storage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class RepositoryTest {
    private final StorageFactory factory;
    private Repository repository;

    public RepositoryTest(StorageFactory factory) {
        this.factory = factory;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data(){
        Collection<Object[]> data = new ArrayList<Object[]>();
        data.add(new Object[]{ new InMemoryStorageFactory() });
        data.add(new Object[]{ new GsonStorageFactory() });
//        data.add(new Object[]{ new RedisStorageFactory() });
        return data;
    }

    @Before
    public void createRepository() {
        repository = new StorageRepository(factory.build());
    }

    @Test
    public void shouldReturnInitialStateWhenNothingWasLoaded() {
        State state = repository.load();

        assertThat(state, is(new State("", 0)));
    }

    @Test
    public void shouldReturnStoredStateWhenSomethingWasLoaded() {
        repository.store(new State("()()()", 0));

        State state = repository.load();

        assertThat(state, is(new State("()()()", 0)));
    }
}

interface StorageFactory {
    Storage build();
}

class InMemoryStorageFactory implements StorageFactory {
    @Override
    public Storage build() {
        return new InMemoryStorage();
    }

    @Override
    public String toString() {
        return "InMemoryStorageFactory";
    }
}

class GsonStorageFactory implements StorageFactory {
    @Override
    public Storage build() {
        return new GsonStorage(new Gson());
    }

    @Override
    public String toString() {
        return "GsonStorageFactory";
    }
}

//class RedisStorageFactory implements StorageFactory {
//    private Jedis jedis;
//
//    {
//        jedis = new Jedis("localhost", 6379);
//    }
//
//    @Override
//    public Storage build() {
//
//        return new RedisStorage(jedis, new Gson());
//    }
//
//    @Override
//    public String toString() {
//        return "RedisStorageFactory";
//    }
//}