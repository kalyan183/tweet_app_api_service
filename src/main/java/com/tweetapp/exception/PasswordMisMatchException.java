package com.tweetapp.exception;

/**
 * @author Kalyan Kommulapati  (kkommulapati@opsecsecurityonline.com)
 * @project tweet_app_api_service
 * @since 07/07/2022 - 02:03 PM
 */
public class PasswordMisMatchException extends Exception {

    public PasswordMisMatchException(String msg) {
        super(msg);
    }

}
