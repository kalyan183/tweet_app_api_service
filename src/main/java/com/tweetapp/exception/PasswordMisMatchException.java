package com.tweetapp.exception;

/**
 * @author Kalyan Kommulapati  (kkommulapati@opsecsecurityonline.com)
 * @project tweet_app_api_service
 * @since 07/07/2022 - 02:03 PM
 */
public class PasswordMisMatchException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 298719729521056195L;

	public PasswordMisMatchException(String msg) {
        super(msg);
    }

}
