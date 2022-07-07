package com.tweetapp.controllers;

import com.tweetapp.dto.ErrorResponse;
import com.tweetapp.dto.Reply;
import com.tweetapp.dto.TweetUpdate;
import com.tweetapp.entities.Tweet;
import com.tweetapp.exception.InvalidUsernameException;
import com.tweetapp.exception.TweetDoesNotExistException;
import com.tweetapp.services.TweetService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kalyan Kommulapati
 * @project TweetApp-API
 * @since 06/07/2022 - 08:37 PM
 */
@Log4j2
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserTweetController {


    private final TweetService tweetService;


    public UserTweetController(@Qualifier("tweet-service") TweetService tweetService) {
        this.tweetService = tweetService;
    }

    // Method to post a new tweet
    @PostMapping(value = "/tweets/{username}/add")
    public ResponseEntity<?> postNewTweet(@PathVariable("username") String username, @RequestBody Tweet newTweet) {
        return new ResponseEntity<>(tweetService.postNewTweet(username, newTweet), HttpStatus.CREATED);

    }

    // Method to retrieve all tweets
    @GetMapping(value = "/tweets/all")
    public ResponseEntity<?> getAllTweets() {

        try {
            log.debug("getting all the tweets..");
            return new ResponseEntity<>(tweetService.getAllTweets(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse("Application has faced an issue"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Method to get a user's tweets
    @GetMapping(value = "/tweets/{username}")
    public ResponseEntity<?> getUserTweets(@PathVariable("username") String username,
                                           @RequestHeader(value = "loggedInUser") String loggedInUser) {
        try {
            log.debug("getting the tweets for user: {}", username);
            return new ResponseEntity<>(tweetService.getUserTweets(username, loggedInUser), HttpStatus.OK);
        } catch (InvalidUsernameException e) {
            return new ResponseEntity<>(new ErrorResponse("Invalid User param received"), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse("Application has faced an issue"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/tweets/{username}/{tweetId}")
    public ResponseEntity<?> getTweetDetails(@PathVariable("username") String username,
                                             @PathVariable("tweetId") String tweetId) {
        try {
            log.debug("getting the tweet details for user: {}", username);
            return new ResponseEntity<>(tweetService.getTweet(tweetId, username), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Method to update a tweet
    @PutMapping(value = "/tweets/{username}/update")
    public ResponseEntity<?> updateTweet(@PathVariable("username") String userId,
                                         @RequestBody TweetUpdate tweetUpdate) {
        try {
            log.debug("updating the tweet for user: {}", userId);
            return new ResponseEntity<>(tweetService.updateTweet(userId, tweetUpdate.getTweetId(), tweetUpdate.getTweetText()), HttpStatus.OK);
        } catch (TweetDoesNotExistException e) {
            return new ResponseEntity<>(new ErrorResponse("Given tweetId cannot be found"), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse("Application has faced an issue"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Method to delete a tweet
    @DeleteMapping(value = "/tweets/{username}/delete")
    public ResponseEntity<?> deleteTweet(@PathVariable("username") String userId,
                                         @RequestHeader(value = "tweetId") String tweetId) {
        try {
            log.debug("deleting tweet for user: {}", userId);
            return new ResponseEntity<>(tweetService.deleteTweet(tweetId), HttpStatus.OK);
        } catch (TweetDoesNotExistException e) {
            return new ResponseEntity<>(new ErrorResponse("Given tweetId cannot be found"), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse("Application has faced an issue"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Post tweets Like
    @PostMapping(value = "/tweets/{username}/like/{tweetId}")
    public ResponseEntity<?> likeATweet(@PathVariable("username") String username,
                                        @PathVariable(value = "tweetId") String tweetId) {
        try {
            log.debug("liking tweet for user: {}", username);
            return new ResponseEntity<>(tweetService.likeTweet(username, tweetId), HttpStatus.OK);
        } catch (TweetDoesNotExistException e) {
            return new ResponseEntity<>(new ErrorResponse("Given tweetId cannot be found"), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse("Application has faced an issue"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // dislike a tweet
    @PostMapping(value = "/tweets/{username}/dislike/{tweetId}")
    public ResponseEntity<?> dislikeATweet(@PathVariable("username") String username,
                                           @PathVariable(value = "tweetId") String tweetId) {
        try {
            log.debug("disliking tweet for user: {}", username);
            return new ResponseEntity<>(tweetService.dislikeTweet(username, tweetId), HttpStatus.OK);
        } catch (TweetDoesNotExistException e) {
            return new ResponseEntity<>(new ErrorResponse("Given tweetId cannot be found"), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse("Application has faced an issue"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Post tweet comment
    @PostMapping(value = "/tweets/{username}/reply/{tweetId}")
    public ResponseEntity<?> replyToTweet(@PathVariable("username") String userId,
                                          @PathVariable("tweetId") String tweetId, @RequestBody Reply tweetReply) {
        try {
            log.debug("replying to the tweet for user: {}", userId);
            return new ResponseEntity<>(tweetService.replyTweet(userId, tweetId, tweetReply.getComment()), HttpStatus.OK);
        } catch (TweetDoesNotExistException e) {
            return new ResponseEntity<>(new ErrorResponse("Given tweetId cannot be found"), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse("Application has faced an issue"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
