package com.ssafy.api.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ProfileNickNameRes {
    @ApiModelProperty(name = "유저 닉네임")
    String nickName;

    @ApiModelProperty(name = "작가 지역")
    String location;

    public static ProfileNickNameRes of(String nickName, String location) {
        ProfileNickNameRes body = new ProfileNickNameRes();
        body.setNickName(nickName);
        body.setLocation(location);
        return body;
    }
}

