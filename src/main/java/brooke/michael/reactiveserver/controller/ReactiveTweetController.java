package brooke.michael.reactiveserver.controller;

import brooke.michael.reactiveserver.model.Tweet;
import brooke.michael.reactiveserver.service.ReactiveTweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/reactive/tweet")
public class ReactiveTweetController {

    private ReactiveTweetService reactiveTweetService;

    @Autowired
    public ReactiveTweetController(ReactiveTweetService reactiveTweetService) {
        this.reactiveTweetService = reactiveTweetService;
    }

    /*
        We set the HTTP headers to indicate that this endpoint is going to produce Text-based Server-Side Events in the form of an Event Stream.
        The requesting client must make sure to set their accept header to text/event-stream to consume this endpoint.
        Returning the Flux type allows us to continually send Tweet values as the service creates them.
     */
    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Tweet> getTweets() {
        return reactiveTweetService.getTweets();
    }
}
