#!/usr/bin/env bash

set -e

docker pull vfarcic/books-ms-tests

docker pull 10.100.198.200:5000/books-ms

docker pull mongo