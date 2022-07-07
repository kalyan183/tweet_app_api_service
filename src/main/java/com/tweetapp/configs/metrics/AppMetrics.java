package com.tweetapp.configs.metrics;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Responsible to hold Prometheus Counter Metrics.
 *
 * @author Kalyan Kommulapati
 * @project tweet_app_api_service.
 * @since 02/07/2022 - 09:35 PM
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class AppMetrics {

    public abstract static class Type {

        public abstract static class Login {

            public static final String KEY = "tweet.app.user.account.login";
        }

        public abstract static class Register {

            public static final String KEY = "tweet.app.user.account.register";
        }

        public abstract static class Post {

            public static final String KEY = "tweet.app.user.tweets.post";
        }

        public abstract static class Update {

            public static final String KEY = "tweet.app.user.tweets.update";
        }

        public abstract static class Delete {

            public static final String KEY = "tweet.app.user.tweets.delete";
        }

        public abstract static class Like {

            public static final String KEY = "tweet.app.user.tweets.like";
        }

        public abstract static class Dislike {

            public static final String KEY = "tweet.app.user.tweets.dislike";
        }
    }
}
