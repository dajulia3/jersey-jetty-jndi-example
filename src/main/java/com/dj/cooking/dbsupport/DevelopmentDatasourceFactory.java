package com.dj.cooking.dbsupport;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.SQLException;

public class DevelopmentDatasourceFactory {
    public MysqlDataSource make() throws SQLException {
        MysqlDataSource ds = new MysqlDataSource();
        ds.setUser("devUser");
        ds.setPassword("secretpassword");
        ds.setURL("jdbc:mysql://localhost:6306/development");
        return ds;
    }
}
