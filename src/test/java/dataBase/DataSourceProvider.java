package dataBase;

import config.ConfigCenter;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;

public enum DataSourceProvider {
    INSTANCE;

    private PGSimpleDataSource dataSource;

    public DataSource getDataSource() {
        if (dataSource == null){
            dataSource = new PGSimpleDataSource();
            dataSource.setServerName(ConfigCenter.configDB.serverName());
            dataSource.setPortNumber(ConfigCenter.configDB.portNumber());
            dataSource.setUser(ConfigCenter.configDB.user());
            dataSource.setDatabaseName(ConfigCenter.configDB.databaseName());
            dataSource.setPassword(ConfigCenter.configDB.password());
        }
        return dataSource;
    }
}
