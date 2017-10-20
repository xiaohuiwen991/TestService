package com.hisign.code.model.database;

/**
 * 用户连接
 * @author jiangpeng
 * @since 2017/5/24 14:56
 */
public class UserConnection {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 连接名
     */
    private String connectionName;

    public UserConnection() {
    }


    public UserConnection(String userName, String connectionName) {
        this.userName = userName;
        this.connectionName = connectionName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getConnectionName() {
        return connectionName;
    }

    public void setConnectionName(String connectionName) {
        this.connectionName = connectionName;
    }
}
