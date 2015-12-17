```bash
vagrant up

vagrant ssh cje

ansible-playbook /vagrant/ansible/cje.yml -c local

ansible-playbook /vagrant/ansible/node.yml -i /vagrant/ansible/hosts/dev

exit

vagrant ssh cje

docker pull vfarcic/books-ms-tests

docker tag vfarcic/books-ms-tests 10.100.198.200:5000/books-ms-tests

docker push 10.100.198.200:5000/books-ms-tests
```

* docker-pipeline > pipeline

```bash
curl -I 10.100.199.201:8080/api/v1/books

curl http://10.100.198.200:8080/docker-traceability/submitContainerStatus \
    --data-urlencode inspectData="$(docker inspect booksms_app_1)"

curl -XGET 'http://10.100.198.200:8080/checkJobName?value=docker-pipeline'

vagrant destroy -f
```