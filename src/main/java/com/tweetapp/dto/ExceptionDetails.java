package com.tweetapp.dto;


import lombok.Data;

import java.util.Date;


/**
 * @author Kalyan Kommulapati
 * @project TweetApp-API
 * @since 06/07/2022 - 08:37 PM
 */
@Data
public class ExceptionDetails {

    /**
     * inshore summary of the error  message occurred.
     */
    private final String shortMessage;

    /**
     * long message about why the error occurred.
     */
    private final String longMessage;

    /**
     * uri of the endpoint.
     */
    private final String resourcePath;

    /**
     * time stamp of the event occurred.
     */
    private final Date timeStamp;

    public ExceptionDetails(final String pShortMessage, final String pLongMessage,
                            final String pResourcePath, final Date pTimeStamp) {
        shortMessage = pShortMessage;
        longMessage = pLongMessage;
        resourcePath = pResourcePath;
        timeStamp = pTimeStamp;
    }
}
