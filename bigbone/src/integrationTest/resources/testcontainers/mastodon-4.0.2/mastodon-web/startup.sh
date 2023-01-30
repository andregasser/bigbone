#!/bin/bash

/provision.sh
rm -f /mastodon/tmp/pids/server.pid
bundle exec rails s -p 3000
