package com.dj.cooking.dbsupport;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.SQLException;

public class TestDatasourceFactory {
    public MysqlDataSource make() throws SQLException {
        MysqlDataSource ds = new MysqlDataSource();
        ds.setUser("testUser");
        ds.setPassword("secretpassword");
        ds.setURL("jdbc:mysql://127.0.0.1:6306/test");
        return ds;
    }
}
