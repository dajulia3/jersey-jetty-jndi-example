CREATE DATABASE IF NOT EXISTS test;
CREATE DATABASE IF NOT EXISTS development;

GRANT ALL PRIVILEGES ON test.* TO 'testUser'@'%' IDENTIFIED BY 'secretpassword' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON development.* TO 'devUser'@'%' IDENTIFIED BY 'secretpassword' WITH GRANT OPTION;