package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.Map;

@Getter @Setter
@ApiModel("MainPageProfileResponse")
public class MainPageProfileRes extends BaseResponseBody {
    @ApiModelProperty(name = "프로필 사진", example = "file")
    File profile;

    @ApiModelProperty(name = "닉네임", example = "")
    String nickName;

    public static MainPageProfileRes of(Integer statusCode, String message, File profile, String nickName) {
        MainPageProfileRes body = new MainPageProfileRes();
        body.setStatusCode(statusCode);
        body.setMessage(message);
        body.setProfile(profile);
        body.setNickName(nickName);
        return body;
    }
}
