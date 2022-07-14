package com.tweetapp.exception;

/**
 * @author Kalyan Kommulapati
 * @project TweetApp-API
 * @since 06/07/2022 - 08:37 PM
 */
public class UsernameAlreadyExists extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7594046056061103234L;

	public UsernameAlreadyExists(String msg) {
        super(msg);
    }
}
