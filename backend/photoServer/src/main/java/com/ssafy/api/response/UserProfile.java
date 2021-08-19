package com.ssafy.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@ApiModel("UserProfile")
public class UserProfile {
    @ApiModelProperty(name = "유저 닉네임")
    String nickName;

    @ApiModelProperty(name = "프로필 사진 경로")
    String photo;

    public static UserProfile of(String nickName, String photo) {
        UserProfile body = new UserProfile();
        body.setNickName(nickName);
        body.setPhoto(photo);
        return body;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "nickName='" + nickName + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
