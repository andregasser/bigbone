FROM alpine:3.15
COPY provision.sql /provision.sql
RUN apk --no-cache add postgresql12-client
ENTRYPOINT [ "psql", "-h", "db", "-U", "mastodon", "-d", "mastodon", "-a", "-f", "/provision.sql" ]
