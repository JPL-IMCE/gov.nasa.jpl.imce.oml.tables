#!/bin/bash

set -ev

# Deploy if TRAVIS_TAG is set.
# Error if TRAVIS_SECURE_ENV_VARS is false

[ -z "${TRAVIS_TAG}" ] && exit 0;
[ ! ${TRAVIS_SECURE_ENV_VARS} ] && exit 255;

openssl aes-256-cbc -md md5 -pass pass:$ENCRYPTION_PASSWORD -in secring.gpg.enc -out local.secring.gpg -d
openssl aes-256-cbc -md md5 -pass pass:$ENCRYPTION_PASSWORD -in pubring.gpg.enc -out local.pubring.gpg -d
openssl aes-256-cbc -md md5 -pass pass:$ENCRYPTION_PASSWORD -in credentials.sbt.enc -out local.credentials.sbt -d
openssl aes-256-cbc -md md5 -pass pass:$ENCRYPTION_PASSWORD -in deploy_key.enc -out local.deploy_key -d
openssl aes-256-cbc -md md5 -pass pass:$ENCRYPTION_PASSWORD -in .npmrc.enc -out .npmrc -d

chmod 600 local.*
eval `ssh-agent -s`
ssh-add local.deploy_key
git config --global push.default simple
git config --global user.email "nobody@nobody.org"
git config --global user.name "Travis CI"

export CI=true

export JVM_OPTS=@travis/jvmopts.compile
sbt -Dproject.version=$TRAVIS_TAG compile test tablesJVM/doc publishSigned fullOptJS tablesJVM/ghpagesPushSite < /dev/null
npm publish


