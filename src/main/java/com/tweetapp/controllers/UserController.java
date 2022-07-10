package com.tweetapp.controllers;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.dto.AuthenticationResponse;
import com.tweetapp.dto.NewPassword;
import com.tweetapp.services.UserOperationsService;

/**
 * @author Kalyan Kommulapati
 * @project TweetApp-API
 * @since 06/07/2022 - 08:37 PM
 */
@Log4j2
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {

    private final UserOperationsService userModelService;

    public UserController(@Qualifier("user-model-service") UserOperationsService userModelService) {
        this.userModelService = userModelService;
    }

    //method to update user password
    @PutMapping(value = "/tweets/{username}/forgot", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> changePassword(@PathVariable("username") String username,
                                            @RequestBody NewPassword newPassword) {
        try {
            log.debug("changing password for user: {}", username);
            return new ResponseEntity<>(userModelService.changePassword(username, newPassword.getNewPassword(), newPassword.getContact()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new AuthenticationResponse("Unable to change password"), HttpStatus.BAD_REQUEST);
        }
    }

    // Method to retrieve all users list
    @GetMapping(value = "/tweets/users/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getAllUsers() {
        log.debug("fetching total users");
        return new ResponseEntity<>(userModelService.getAllUsers(), HttpStatus.OK);

    }

    //method to search for like users by username
    @GetMapping(value = "/tweets/user/search/{username}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> searchForUsers(@PathVariable String username) {
        log.debug("fetching user by userName: {}", username);
        return new ResponseEntity<>(userModelService.getUsersByUsername(username), HttpStatus.OK);
    }
}
