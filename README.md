Install MySQL on your OS:
	1. For Mac download: https://dev.mysql.com/downloads/mysql/
	
Make sure mysql is in your PATH variables. 
	1. For Mac: /usr/local/mysql/bin
	
Setup MySQL

mysql -u root -p

create user 'shagunbandi'@'localhost' identified by 'Welcome#123'; 

create database playground;

grant all on playground.* to 'shagunbandi'@'localhost'; 

FLUSH PRIVILEGES;