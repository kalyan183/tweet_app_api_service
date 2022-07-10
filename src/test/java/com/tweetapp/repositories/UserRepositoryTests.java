package com.tweetapp.repositories;

import com.tweetapp.entities.UserModel;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @author Kalyan Kommulapati
 * @project tweet_app_api_service
 * @since 09/07/2022 - 04:14 PM
 */
@DataMongoTest
@RunWith(SpringRunner.class)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;


    @Test
    public void testSaveUser() {
        final UserModel userModel = UserModel.builder()
                .username("ikalyan183@gmail.com")
                .firstName("kalyan")
                .lastName("says")
                .email("ikalyan183@gmail.com")
                .contactNum("12345678910")
                .password("password")
                .build();
        userRepository.save(userModel);
        Assertions.assertThat(userModel.getUsername()).isEqualTo("ikalyan183@gmail.com");
    }

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

    @Test
    public void testSearchByUserName() {
        final String userName = "kalyan";

        final List<UserModel> userModelList = Arrays.asList(
                UserModel.builder()
                        .username("ikalyan183@gmail.com")
                        .firstName("kalyan")
                        .lastName("says")
                        .email("ikalyan183@gmail.com")
                        .build(),
                UserModel.builder()
                        .username("kalyan@gmail.com")
                        .firstName("kalyan")
                        .lastName("here")
                        .email("kalyan@gmail.com")
                        .build()
        );

        userRepository.searchByUsername(userName);
        Assertions.assertThat(userModelList.size()).isGreaterThanOrEqualTo(2);
    }
}
