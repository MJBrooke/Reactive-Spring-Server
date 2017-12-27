package brooke.michael.reactiveserver.service;

import brooke.michael.reactiveserver.model.Tweet;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

@Service
public class ReactiveTweetService {

    private static final int MIN_RANDOM_INT_VALUE = 0;
    private static final int MAX_RANDOM_INT_VALUE = 99999;
    private static final long TWEET_DELAY = 3;

    private static int tweetCounter = 0;

    public Flux<Tweet> getTweets() {
        return Flux.fromStream( // We generate a Flux with infinite values to be returned by the service
                // Stream.generate creates infinite values in a Stream as created by the lambda supplier method (which creates Tweets, in this case)
                Stream.generate(() -> new Tweet(getRandomId(), LocalDate.now(), getContent()))
        ).delayElements(Duration.ofSeconds(TWEET_DELAY)); //For sanity's sake, we only output the Flux Tweet every 3 seconds
    }

    private int getRandomId() {
        return ThreadLocalRandom.current().nextInt(MIN_RANDOM_INT_VALUE, MAX_RANDOM_INT_VALUE + 1);
    }

    private String getContent() {
        return "This is tweet #" + tweetCounter++;
    }
}
