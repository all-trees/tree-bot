package nl.dvberkel;

import nl.dvberkel.bot.Bot;
import nl.dvberkel.configuration.BotConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Runner {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(BotConfiguration.class);

        Bot bot = context.getBean(Bot.class);

        bot.next();
    }
}
