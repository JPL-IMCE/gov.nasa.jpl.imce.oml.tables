#!/bin/bash

set -ev

if [ -d ~/.nvm ]; then
  echo "# nvm is already installed"
else
  echo "# Installing nvm..."
  git clone https://github.com/creationix/nvm.git ~/.nvm
  (cd ~/.nvm && git checkout `git describe --abbrev=0 --tags`)
  source ~/.nvm/nvm.sh
  nvm install $TRAVIS_NODE_VERSION
fi
