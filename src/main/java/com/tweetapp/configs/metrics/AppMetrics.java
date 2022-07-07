package com.tweetapp.configs.metrics;

/**
 * Responsible to hold Prometheus Counter Metrics.
 *
 * @author Kalyan Kommulapati
 * @project tweet_app_api_service.
 * @since 02/07/2022 - 09:35 PM
 */
public abstract class AppMetrics {

    public static abstract class Type {

        public static abstract class Login {

            public static final String KEY = "tweet.app.user.account.login";
        }

        public static abstract class Register {

            public static final String KEY = "tweet.app.user.account.register";
        }

        public static abstract class Post {

            public static final String KEY = "tweet.app.user.tweets.post";
        }

        public static abstract class Update {

            public static final String KEY = "tweet.app.user.tweets.update";
        }

        public static abstract class Reply {

            public static final String KEY = "tweet.app.user.tweets.reply";
        }

        public static abstract class Delete {

            public static final String KEY = "tweet.app.user.tweets.delete";
        }

        public static abstract class Like {

            public static final String KEY = "tweet.app.user.tweets.like";
        }

        public static abstract class Dislike {

            public static final String KEY = "tweet.app.user.tweets.dislike";
        }
    }
}
