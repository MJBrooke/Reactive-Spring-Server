package brooke.michael.reactiveserver.service;

import brooke.michael.reactiveserver.model.Tweet;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

@Service
public class ReactiveTweetService {

    private static final int MIN_RANDOM_INT_VALUE = 0;
    private static final int MAX_RANDOM_INT_VALUE = 99999;

    private static int tweetCounter = 0;

    public Flux<Tweet> getTweets() {

        //Create a Flux that continually generates new Tweets with different values
        Flux<Tweet> tweetsPublisher = Flux.fromStream(Stream.generate(
                () -> new Tweet(getRandomId(), LocalDate.now(), getContent()))
        );

        //Create a Flux that continually generates a (Long) value every 3 seconds
        Flux<Long> serverSideEventInterval = Flux.interval(Duration.ofSeconds(3));

        /*
            Takes 2 publishers, and continually waits for each to generate a single value before combining them into a single Tuple.
            Once both have generated a value, we return the second value of the Tuple which is the Tweet object.
            The Interval Flux ensures that these Tweets only generate every 3 seconds.
         */
        return Flux.zip(serverSideEventInterval, tweetsPublisher).map(Tuple2::getT2);
    }

    private int getRandomId() {
        return ThreadLocalRandom.current().nextInt(MIN_RANDOM_INT_VALUE, MAX_RANDOM_INT_VALUE + 1);
    }

    private String getContent() {
        return "This is tweet #" + tweetCounter++;
    }


}
