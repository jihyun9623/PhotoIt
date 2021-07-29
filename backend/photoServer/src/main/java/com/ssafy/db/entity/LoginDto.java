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
public class LoginDto {
    @ApiModelProperty(value = "유저 아이디")
    private String userId;

    @ApiModelProperty(value = "유저 비밀번호")
    private String userPw;
}
