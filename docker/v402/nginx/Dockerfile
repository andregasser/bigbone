FROM nginx:stable-alpine
RUN rm /etc/nginx/conf.d/default.conf
COPY mastodon.conf /etc/nginx/conf.d/mastodon.conf
COPY localhost.pem /etc/ssl/certs/localhost.pem
COPY localhost-key.pem /etc/ssl/certs/private/localhost-key.pem

