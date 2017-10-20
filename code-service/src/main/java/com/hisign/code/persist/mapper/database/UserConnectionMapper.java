package com.hisign.code.persist.mapper.database;

import com.hisign.code.model.database.UserConnection;

/**
 *
 * @author jiangpeng
 * @since 2017/5/24 15:01
 */
public interface UserConnectionMapper {

    void deleteUserConnection(UserConnection userConnection);

    void insertUserConnection(UserConnection userConnection);
}
