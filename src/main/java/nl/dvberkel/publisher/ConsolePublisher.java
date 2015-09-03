package nl.dvberkel.publisher;

public class ConsolePublisher implements Publisher{
    @Override
    public void publish(String word) {
        System.out.println(String.format("word: %s", word));
    }
}
