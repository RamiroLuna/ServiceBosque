package org.probosque.model.json;

import org.probosque.dto.UserLoginDTO;

public class DataLoginJson {
    private UserLoginDTO userInfo;

    public UserLoginDTO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserLoginDTO userInfo) {
        this.userInfo = userInfo;
    }
}