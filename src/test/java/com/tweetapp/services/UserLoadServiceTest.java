package com.tweetapp.services;

import com.tweetapp.entities.UserModel;
import com.tweetapp.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author Kalyan Kommulapati
 * @project tweet_app_api_service
 * @since 09/07/2022 - 05:03 PM
 */
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class UserLoadServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserLoadService userLoadService;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFetchUserByName() {
        final String userName = "ikalyan183@gmail.com";
        final UserModel user = UserModel.builder()
                .username("ikalyan183@gmail.com")
                .password("password")
                .build();
        Mockito.when(userRepository.findByUsername(userName)).thenReturn(user);
        UserDetails userModel = userLoadService.loadUserByUsername(userName);
        Assert.assertEquals(user.getUsername(), userModel.getUsername());
    }
}
