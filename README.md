```bash
vagrant ssh cje

ansible-playbook /vagrant/ansible/cje.yml -c local

ansible-playbook /vagrant/ansible/node.yml -i /vagrant/ansible/hosts/dev

vagrant destroy -f

# Jenkins OSS: https://wiki.jenkins-ci.org/display/JENKINS/CloudBees+Docker+Custom+Build+Environment+Plugin

# Jenkins OSS: https://wiki.jenkins-ci.org/display/JENKINS/CloudBees+Docker+Build+and+Publish+plugin
```