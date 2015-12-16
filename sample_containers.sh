#!/bin/bash

docker pull vfarcic/books-ms
docker tag vfarcic/books-ms 10.100.198.201:5000/books-ms
docker push 10.100.198.201:5000/books-ms
