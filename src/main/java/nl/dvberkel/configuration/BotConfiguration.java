package nl.dvberkel.configuration;

import nl.dvberkel.bot.Bot;
import nl.dvberkel.dyck.Generator;
import nl.dvberkel.publisher.ConsolePublisher;
import nl.dvberkel.publisher.Publisher;
import nl.dvberkel.repository.Repository;
import nl.dvberkel.repository.StorageRepository;
import nl.dvberkel.repository.storage.InMemoryStorage;
import nl.dvberkel.repository.storage.Storage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BotConfiguration {

    @Bean
    public Bot createBot() {
        return new Bot(createGenerator(), createRepository(), createPublisher());
    }

    @Bean
    public Generator createGenerator() {
        return new Generator("(", ")");
    }

    @Bean
    public Repository createRepository() {
        Storage storage = createStorage();
        return new StorageRepository(storage);
    }

    private Storage createStorage() {
        return new InMemoryStorage();
    }

    @Bean
    public Publisher createPublisher() {
        return new ConsolePublisher();
    }
}
