package com.ssafy.api.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ProfileNickNameRes {
    @ApiModelProperty(name = "프로필 사진")
    String photo;

    @ApiModelProperty(name = "작가 닉네임")
    String nickName;

    public static ProfileNickNameRes of(String photo, String nickName) {
        ProfileNickNameRes body = new ProfileNickNameRes();
        body.setNickName(nickName);
        body.setPhoto(photo);
        return body;
    }
}

