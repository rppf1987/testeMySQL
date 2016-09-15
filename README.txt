Imagem utilizada no Vagrant:
psysdev/basebox-ubuntu-14.04-java8-mysql
https://atlas.hashicorp.com/psysdev/boxes/basebox-ubuntu-14.04-java8-mysql

Para subir o Vagrant, com o Virtualbox já instalado: 
vagrant box add psysdev/boxes/basebox-ubuntu-14.04-java8-mysql;
vagrant init psysdev/boxes/basebox-ubuntu-14.04-java8-mysql;
vagrant up

# sudo apt-get --reinstall install mysql-server-5.5

Acessado o mysql com o usuário root,
mysql -u root -p (password) databasename < employees.sql

Pronto, o server já está funcionando e pronto para ser acessado pelo jar ConnectMySQL.jar

Para rodar: java -jar ConnectMySQL.jar
