#!/bin/bash

echo "Migrating database..."
bundle exec rake db:migrate

echo "Checking if provisioning is required..."
bin/tootctl accounts approve $MASTODON_ADMIN_USERNAME

if [ $? -eq 0 ]; then
    echo "Provisioning not required"
else
    echo "Provisioning mastodon..."

    bin/tootctl accounts create $MASTODON_ADMIN_USERNAME --email $MASTODON_ADMIN_EMAIL --confirmed --role Owner

    echo "Provisioning done"
fi
