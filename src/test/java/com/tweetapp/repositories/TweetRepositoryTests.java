package com.tweetapp.repositories;

import com.tweetapp.entities.UserModel;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Kalyan Kommulapati
 * @project tweet_app_api_service
 * @since 09/07/2022 - 04:45 PM
 */
@DataMongoTest
@RunWith(SpringRunner.class)
public class TweetRepositoryTests {

    @Autowired
    private TweetRepository userRepository;

    @Test
    public void testFindUserByName() {
        final String username = "ikalyan183@gmail.com";
        final UserModel user = UserModel.builder()
                .username("ikalyan183@gmail.com")
                .firstName("kalyan")
                .lastName("says")
                .email("ikalyan183@gmail.com")
                .build();
        userRepository.findByUsername(username);
        Assertions.assertThat(username).isEqualTo(user.getEmail());
    }

}
