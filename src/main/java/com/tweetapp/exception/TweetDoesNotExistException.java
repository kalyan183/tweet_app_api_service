package com.tweetapp.exception;

/**
 * @author Kalyan Kommulapati
 * @project TweetApp-API
 * @since 06/07/2022 - 08:37 PM
 */
public class TweetDoesNotExistException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -179246759582276729L;

	public TweetDoesNotExistException(String msg) {
		super(msg);
	}
}
