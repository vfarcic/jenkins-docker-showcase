# -*- mode: ruby -*-
# vi: set ft=ruby :

VAGRANTFILE_API_VERSION = "2"

Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|
  config.vm.synced_folder ".", "/vagrant"
  # config.vm.synced_folder ".", "/vagrant", mount_options: ["dmode=700,fmode=600"]
  config.vm.define "cje" do |d|
    d.vm.box = "ubuntu/trusty64"
    d.vm.hostname = "cje"
    d.vm.network "private_network", ip: "10.100.198.201"
    d.vm.provision :shell, path: "bootstrap_ansible.sh"
    d.vm.provision :shell, inline: "PYTHONUNBUFFERED=1 ansible-playbook /vagrant/ansible/cje.yml -c local"
    d.vm.provision :shell, inline: "sample_containers.sh"
    d.vm.provider "virtualbox" do |v|
      v.memory = 2048
    end
  end
  (1..3).each do |i|
    config.vm.define "node-#{i}" do |d|
      d.vm.box = "ubuntu/trusty64"
      d.vm.hostname = "node-#{i}"
      d.vm.network "private_network", ip: "10.100.199.20#{i}"
      d.vm.provider "virtualbox" do |v|
        v.memory = 2048
      end
    end
  end
  if Vagrant.has_plugin?("vagrant-cachier")
    config.cache.scope = :box
  end
end