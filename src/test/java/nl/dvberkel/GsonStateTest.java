package nl.dvberkel;

import com.google.gson.Gson;
import nl.dvberkel.dyck.State;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class GsonStateTest {
    private final State original;
    private Gson gson;

    public GsonStateTest(State original) {
        this.original = original;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        ArrayList<Object[]> data = new ArrayList<Object[]>();
        data.add(new Object[]{ new State("", 0) });
        data.add(new Object[]{ new State("()", 0) });
        data.add(new Object[]{ new State("()()", 0) });
        data.add(new Object[]{ new State(new State("()()", 3), "(())", 0) });
        return data;
    }

    @Before()
    public void createGson() {
        gson = new Gson();
    }

    @Test
    public void shouldSurviveAfterGsonRoundTrip() {
        String json = gson.toJson(original);

        assertThat(gson.fromJson(json, State.class), is(original));
    }
}
