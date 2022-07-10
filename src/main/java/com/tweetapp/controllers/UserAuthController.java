package com.tweetapp.controllers;

import com.tweetapp.configs.metrics.AppConstants;
import com.tweetapp.configs.metrics.AppMetrics;
import com.tweetapp.dto.AuthenticationRequest;
import com.tweetapp.dto.AuthenticationResponse;
import com.tweetapp.entities.UserModel;
import com.tweetapp.exception.UsernameAlreadyExists;
import com.tweetapp.services.UserOperationsService;
import io.micrometer.core.instrument.MeterRegistry;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kalyan Kommulapati
 * @project TweetApp-API
 * @since 06/07/2022 - 08:37 PM
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@Api
@Log4j2
public class UserAuthController {

    private final UserOperationsService userModelService;

    private final MeterRegistry meterRegistry;

    private final AuthenticationManager authenticationManager;

    public UserAuthController(@Qualifier("user-model-service") UserOperationsService userModelService,
                              MeterRegistry meterRegistry,
                              AuthenticationManager authenticationManager) {
        this.userModelService = userModelService;
        this.meterRegistry = meterRegistry;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping(value = "/tweets/register", consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.TEXT_PLAIN_VALUE,
            MediaType.ALL_VALUE
    },
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.TEXT_PLAIN_VALUE,
                    MediaType.ALL_VALUE
            })
    public ResponseEntity<?> subscribeClient(@RequestBody UserModel userModel) {

        try {
            UserModel savedUser = userModelService.createUser(userModel);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (UsernameAlreadyExists e) {
            return new ResponseEntity<>(new AuthenticationResponse("Given userId/email already exists"), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(new AuthenticationResponse(AppConstants.APP_ISSUE),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping(value = "/tweets/login", consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.TEXT_PLAIN_VALUE,
            MediaType.ALL_VALUE
    },
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.TEXT_PLAIN_VALUE,
                    MediaType.ALL_VALUE
            })
    public ResponseEntity<?> authenticateClient(@RequestBody AuthenticationRequest authenticationRequest) {
        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            log.debug("successfully logged in with userName: {}", username);
            meterRegistry.counter(AppMetrics.LOGIN).increment();
        } catch (Exception e) {
            return new ResponseEntity<>(new AuthenticationResponse("Bad Credentials " + username), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(userModelService.findByUsername(username), HttpStatus.OK);
    }
}
