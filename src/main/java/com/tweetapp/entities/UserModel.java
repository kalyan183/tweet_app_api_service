package com.tweetapp.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Kalyan Kommulapati
 * @project TweetApp-API
 * @since 06/07/2022 - 08:37 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "users")
public class UserModel {

    @Id
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String contactNum;
    private String password;

}
