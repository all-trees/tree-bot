package nl.dvberkel.bot;

import nl.dvberkel.dyck.Generator;
import nl.dvberkel.dyck.State;
import nl.dvberkel.publisher.Publisher;
import nl.dvberkel.repository.Repository;

public class Bot {
    private final Generator generator;
    private final Repository repository;
    private final Publisher publisher;

    public Bot(Generator generator, Repository repository, Publisher publisher) {
        this.generator = generator;
        this.repository = repository;
        this.publisher = publisher;
    }


    public void next() {
        State state = repository.load();
        State nextState = generator.next(state);
        publisher.publish(nextState.word);
        repository.store(nextState);
    }
}
