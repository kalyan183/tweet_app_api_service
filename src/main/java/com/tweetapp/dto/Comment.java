package com.tweetapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Kalyan Kommulapati
 * @project TweetApp-API
 * @since 06/07/2022 - 08:37 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    private String username;
    private String comment;

}
