From mysql

ADD  ./database-setup /database-setup

RUN sed -i -e"s/^bind-address\s*=\s*127.0.0.1/bind-address = 0.0.0.0/" /etc/mysql/my.cnf\
mysql -u$MYSQL_USER -p$MYSQL_PASSWORD $MYSQL_DATABASE < /database-setup/schema.sql; \
if [ "$LOAD_FIXTURES" = "true" ]; then \
    mysql -u$MYSQL_USER -p$MYSQL_PASSWORD $MYSQL_DATABASE < /database-setup/test_fixtures.sql; \
fi