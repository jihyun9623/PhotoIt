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
    @ApiModelProperty(name = "프로필 사진", example = "파일 저장 위치")
    UserProfile userProfile;

    public static MainPageProfileRes of(Integer statusCode, String message, UserProfile userProfile) {
        MainPageProfileRes body = new MainPageProfileRes();
        body.setStatusCode(statusCode);
        body.setMessage(message);
        body.setUserProfile(userProfile);
        return body;
    }

    @Override
    public String toString() {
        return "MainPageProfileRes{" +
                "userProfile=" + userProfile +
                '}';
    }
}
