#!/usr/bin/env sh

SERVER_IP=$1
SSH_KEY=$2

echo "$SSH_KEY" >> ssh_key.pub
ssh-add ssh_key
ssh root@$SERVER_IP
echo "Connected to environment server..."

cd workout-rival || echo "ERROR - workout-rival dir not found!"
git checkout master
git pull ; ./build.sh ; docker-compose up -d
echo "SUCCESS!"