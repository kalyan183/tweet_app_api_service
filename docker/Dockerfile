FROM openjdk:8
VOLUME /tmp
EXPOSE 8682
# =============================================================================
#environment
ENV env dev


# =============================================================================
#Project details
ENV artifactId @project.artifactId@
ENV artifact @project.groupId@.@project.artifactId@
ENV version @project.version@
ENV timestamp @timestamp@
ENV groupId @project.groupId@
ENV builtOn @timestamp@

# =============================================================================
#logging
ENV logging.level.com.tweetapp debug
ENV logging.pattern.console %d [%level] %c{1.} {%t} %m%n
ENV logging.file tweet_app_api_service/logs/tweet-app-service.log
ENV logging.pattern.file %d [%level] %c{1.} {%t} %m%n

# =============================================================================
#MongoDB
ENV mongodb.uri mongodb://localhost:27017
ENV mongodb.dbname tweet_app
ENV mongodb.maxIdleConnectionTimeInMillis 30000

# =============================================================================
# Actuator/Prometheus configuration == START ==
ENV management.application.name "Tweet-App-Api-Service"
ENV management.endpoint.health.show-details always
ENV management.endpoint.metrics.enabled true
ENV management.endpoints.enabled-by-default true
ENV management.endpoints.web.base-path /insights
ENV management.endpoints.web.exposure.include *
ENV management.server.port 8071


ADD target/tweet-app-service.jar tweet-app-service.jar

ENTRYPOINT ["java","-jar", "/tweet-app-service.jar","--spring.profiles.active=${env}"]
