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
        We set the HTTP headers to indicate that this endpoint is going to produce JSON Server-Side Events.
        We also then return the Flux type.
     */
    @GetMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Tweet> getTweets() {
        return reactiveTweetService.getTweets();
    }
}
