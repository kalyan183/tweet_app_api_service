package com.tweetapp.exception;

/**
 * @author Kalyan Kommulapati
 * @project TweetApp-API
 * @since 06/07/2022 - 08:37 PM
 */
public class InvalidUsernameException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5966130919267770338L;

	public InvalidUsernameException(String msg){
		super(msg);
	}
	
}
