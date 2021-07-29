package com.ssafy.db.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    @ApiModelProperty(value = "유저 index")
    private int userIdx;
    @ApiModelProperty(value = "유저 아이디")
    private String userId;
    @ApiModelProperty(value = "유저 비밀번호")
    private String userPw;
    @ApiModelProperty(value = "유저 닉네임")
    private String userNickname;
    @ApiModelProperty(value = "유저 작가여부")
    private Boolean userAuthor;
    @ApiModelProperty(value = "유저 프로필사진")
    private String userPhoto;

    @Override
    public String toString() {
        return userIdx+" "+userId+" "+userPw+" "+userNickname+" "+userAuthor+" "+userPhoto;
    }

}
