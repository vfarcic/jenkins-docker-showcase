Preload
=======

```bash
git clone https://github.com/vfarcic/jenkins-docker-showcase.git

cd jenkins-docker-showcase

vagrant up cje node-1 node-2 node-3

vagrant ssh cje -c "chmod +x /vagrant/scripts/*"

vagrant ssh cje -c "ansible-playbook /vagrant/ansible/cje.yml -c local --extra-vars 'skip_licence=true'"

vagrant ssh cje -c "/vagrant/scripts/preload_cje.sh"

vagrant ssh cje -c "ansible-playbook /vagrant/ansible/node.yml -i /vagrant/ansible/hosts/dev" # Answer "yes" when asked

vagrant ssh cje -c "ansible-playbook /vagrant/ansible/nginx.yml -i /vagrant/ansible/hosts/dev" # Answer "yes" when asked

vagrant ssh node-1 -c "/vagrant/scripts/preload_node.sh"

vagrant ssh node-2 -c "/vagrant/scripts/preload_node.sh"

vagrant ssh node-3 -c "/vagrant/scripts/preload_node.sh"

vagrant halt
```

Setup
=====

```bash
vagrant up

vagrant ssh cje

ansible-playbook /vagrant/ansible/node.yml -i /vagrant/ansible/hosts/dev

ansible-playbook /vagrant/ansible/cje.yml -c local

exit

vagrant ssh cje

docker pull vfarcic/books-ms-tests

docker tag vfarcic/books-ms-tests 10.100.198.200:5000/books-ms-tests

docker push 10.100.198.200:5000/books-ms-tests
```

* docker-pipeline > pipeline

```bash
curl -I 10.100.199.201:8080/api/v1/books

exit

vagrant ssh node-1

docker ps

docker rm -f booksms_app_1 booksms_db_1

exit
```

* books-ms-workflow-simple

```bash
curl -I 10.100.199.201:8080/api/v1/books

vagrant ssh node-1

docker ps

docker rm -f booksms_app_1 booksms_db_1

exit

vagrant ssh cje

curl 10.100.199.201:8500/v1/catalog/nodes \
    | jq '.'
```

Open [http://10.100.199.201:8500](http://10.100.199.201:8500)

```bash
export DOCKER_HOST=tcp://10.100.199.201:2375

docker info

docker ps

curl -I 10.100.199.201/api/v1/books
```

* books-ms-workflow

```bash
ansible-playbook /vagrant/ansible/nginx.yml -i /vagrant/ansible/hosts/dev

docker ps -a | grep books

curl -I 10.100.199.201/api/v1/books
```