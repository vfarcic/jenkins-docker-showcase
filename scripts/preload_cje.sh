#!/usr/bin/env bash

set -e

docker pull vfarcic/books-ms-tests

docker tag vfarcic/books-ms-tests 10.100.198.200:5000/books-ms-tests

docker push 10.100.198.200:5000/books-ms-tests

git clone https://github.com/vfarcic/books-ms.git

cd books-ms

docker run -it --rm \
    -v $PWD/client/components:/source/client/components \
    -v $PWD/client/test:/source/client/test \
    -v $PWD/src:/source/src \
    -v $PWD/target/scala-2.10:/source/target/scala-2.10 \
    --env TEST_TYPE=all \
    10.100.198.200:5000/books-ms-tests

docker build -t 10.100.198.200:5000/books-ms .

docker push 10.100.198.200:5000/books-ms

docker pull mongo

docker rmi 10.100.198.200:5000/books-ms-tests

ansible-playbook /vagrant/ansible/cje.yml -c local --extra-vars 'skip_licence=true'