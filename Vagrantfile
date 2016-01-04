# -*- mode: ruby -*-
# vi: set ft=ruby :

VAGRANTFILE_API_VERSION = "2"

Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|
    if (/cygwin|mswin|mingw|bccwin|wince|emx/ =~ RUBY_PLATFORM) != nil
        config.vm.synced_folder ".", "/vagrant", mount_options: ["dmode=700,fmode=600"]
    else
        config.vm.synced_folder ".", "/vagrant"
    end
    config.vm.define "cje" do |d|
        d.vm.box = "ubuntu/trusty64"
        d.vm.hostname = "cje"
        d.vm.network "private_network", ip: "10.100.198.200"
        d.vm.provision :shell, path: "bootstrap_ansible.sh"
        d.vm.provider "virtualbox" do |v|
            v.memory = 3072
        end
    end
    if Vagrant.has_plugin?("vagrant-cachier")
        config.cache.scope = :box
    end
end